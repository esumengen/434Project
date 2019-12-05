package communicationStrategy;

public class CommunicationStrategyFactory {
    public CommunicationStrategy getCommunicationStategy(Strategies strategy) {
        switch (strategy) {
            case S3Video:
                return new S3VideoStrategy();
            case LocalVideo:
                return new VideoFileStrategy();
            case LocalImageFolder:
                return new ImageFolderStrategy();

                default:
                    return null;
        }
    }
}
