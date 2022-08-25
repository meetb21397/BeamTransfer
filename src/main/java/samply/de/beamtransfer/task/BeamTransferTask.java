package samply.de.beamtransfer.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import samply.de.beamtransfer.BeamTransfer.BeamTransfer;
import samply.de.beamtransfer.BeamTransfer.BeamTransferConst;
import samply.de.beamtransfer.BeamTransfer.BeamTransferException;

@Component
public class BeamTransferTask {
    private final Logger logger = LoggerFactory.getLogger(BeamTransferTask.class);

    private BeamTransfer beamTransfer;

    private Path transferFilesDirectory;

    @Autowired
    public void setBeamTransfer(BeamTransfer beamTransfer) {
      this.beamTransfer = beamTransfer;
    }

    @Autowired
    public void setTransferFilesDirectory(
        @Value(BeamTransferConst.TRANSFER_FILES_DIRECTORY_SV) String transferFilesDirectory) {
      this.transferFilesDirectory = Paths.get(transferFilesDirectory);
    }

    /**
     * Transfers all files stored in transfer file directory to a second bridgehead under transfer
     * bridgehead URL and deletes the files if they are successfully transferred.
     */
    public void transfer() {

      logger.info("Sending files to transfer...");
      sendAndDeleteAllPaths();
      logger.info("Sending files ended");

    }

    private void sendAndDeleteAllPaths() {

      try {
        Files
            .walk(transferFilesDirectory)
            .filter(Files::isRegularFile)
            .forEach(this::sendAndDeletePath);

      } catch (IOException e) {
        logger.error(
            "Error reading files to transfer in directory " + transferFilesDirectory.toAbsolutePath(),
            e);
      }

    }

    private void sendAndDeletePath(Path path) {
      try {
        sendAndDeletePath_WithoutManagementException(path);
      } catch (BeamTransferException e) {
        logger.error("Error transferring file " + path.getFileName(), e);
      } catch (IOException e) {
        logger.error("Error deleting file " + path.getFileName(), e);
      }

    }

    private void sendAndDeletePath_WithoutManagementException(Path path)
        throws BeamTransferException, IOException {

      beamTransfer.transfer(path);
      Files.delete(path);

    }


  }
