package samply.de.beamtransfer.BeamTransfer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
public class BeamTransfer {

  private String targetBridgeheadUrl;
  private String targetBridgeheadApiKey;

  private RestTemplate restTemplate = new RestTemplate();

  @Autowired
  public void setTargetBridgeheadUrl(
      @Value(BeamTransferConst.TARGET_BRIDGEHEAD_URL_SV) String targetBridgeheadUrl) {
    this.targetBridgeheadUrl = targetBridgeheadUrl;
  }

  @Autowired
  public void setTargetBridgeheadApiKey(
      @Value(BeamTransferConst.TARGET_BRIDGEHEAD_URL_SV) String targetBridgeheadApiKey) {
    this.targetBridgeheadApiKey = targetBridgeheadApiKey;
  }
  public void transfer(Path path) throws BeamTransferException {

    try {
      HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(createBody(path),
          createHeaders());

      restTemplate.postForEntity(targetBridgeheadUrl, httpEntity,
          String.class);
    } catch (RestClientException e) {
      throw new BeamTransferException(e);
    }
  }

  @Bean
  private HttpHeaders createHeaders() {

    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
    httpHeaders.set(BeamTransferConst.API_KEY_HEADER, targetBridgeheadApiKey);

    return httpHeaders;

  }

  private MultiValueMap<String, Object> createBody(Path path) throws BeamTransferException {

    MultiValueMap<String, String> fileMultiValueMap = new LinkedMultiValueMap<>();
    ContentDisposition contentDisposition = createContentDisposition(path);
    fileMultiValueMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

    HttpEntity<byte[]> fileHttpEntity = new HttpEntity<>(convertToBytes(path), fileMultiValueMap);

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add(BeamTransferConst.TRANSFER_FILE_PARAMETER, fileHttpEntity);

    return body;

  }

  private ContentDisposition createContentDisposition(Path path) {
    return ContentDisposition.builder("form-data").name("file")
        .filename(path.getFileName().toString()).build();
  }

  private byte[] convertToBytes(Path path) throws BeamTransferException {

    try {
      return Files.readAllBytes(path);
    } catch (IOException e) {
      throw new BeamTransferException(e);
    }
  }
}
