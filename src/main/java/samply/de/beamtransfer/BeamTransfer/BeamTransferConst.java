package samply.de.beamtransfer.BeamTransfer;

public class BeamTransferConst {

  public final static String APP_NAME = "file-transfer";

  // HTTP Headers
  public final static String API_KEY_HEADER = "apiKey";

  // REST API path
  public final static String TRANSFER_URL = "/transfer";
  public final static String INFO_URL = "/info";

  // HTTP body keys
  public final static String TRANSFER_FILE_PARAMETER = "file";

  // Environment variables
  public final static String CLIENT_API_KEY = "CLIENT_API_KEY";
  public final static String TRANSFER_FILES_CRON_EXPR = "TRANSFER_FILES_CRON_EXPR";
  public final static String TRANSFER_FILES_DIRECTORY = "TRANSFER_FILES_DIRECTORY";
  public final static String TARGET_BRIDGEHEAD_URL = "TARGET_BRIDGEHEAD_URL";
  public final static String TARGET_BRIDGEHEAD_APIKEY = "TARGET_BRIDGEHEAD_APIKEY";
  public final static String APPLICATION_PORT = "APPLICATION_PORT";

  // Initialization Spring Values: _SV (for annotation @Value)
  public final static String CLIENT_API_KEY_SV = "${" + CLIENT_API_KEY + "}";
  public final static String TRANSFER_FILES_CRON_EXPR_SV =
      "${" + TRANSFER_FILES_CRON_EXPR + ":#{'-'}}";
  public final static String TRANSFER_FILES_DIRECTORY_SV =
      "${" + TRANSFER_FILES_DIRECTORY + ":#{'.'}}";
  public final static String TARGET_BRIDGEHEAD_URL_SV = "${" + TARGET_BRIDGEHEAD_URL + ":#{null}}";
  public final static String TARGET_BRIDGEHEAD_APIKEY_SV =
      "${" + TARGET_BRIDGEHEAD_APIKEY + ":#{null}}";

}

