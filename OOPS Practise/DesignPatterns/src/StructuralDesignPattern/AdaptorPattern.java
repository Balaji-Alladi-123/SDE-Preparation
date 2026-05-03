package StructuralDesignPattern;

interface MediaPlayer {
    void playSongs(String auidoType, String file);
}

class AdvancedMediaPlayer {
    public void playMp4Songs(String file) {
        System.out.println("Playing mp4 audio songs");
    }
}

class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMusicPlayer;

    MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new AdvancedMediaPlayer();
        }
    }

    @Override
    public void playSongs(String audioType, String file) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4Songs(file);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void playSongs(String audioType, String file) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + file);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.playSongs(audioType, file);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}


public class AdaptorPattern {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.playSongs("mp3", "beyond the horizon.mp3");
        audioPlayer.playSongs("mp4", "alone.mp4");
        audioPlayer.playSongs("vlc", "far far away.vlc");
    }
}

