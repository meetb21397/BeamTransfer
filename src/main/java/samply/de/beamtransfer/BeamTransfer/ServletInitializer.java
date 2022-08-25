package samply.de.beamtransfer.BeamTransfer;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

  /*
   *  ServletInitializer : this class called ServletInitializer extends the SpringBootServletInitializer and overrides the configure() method.
   */

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(BeamTransferApplication.class);
  }
}
