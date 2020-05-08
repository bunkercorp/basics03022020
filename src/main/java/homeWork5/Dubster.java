package homeWork5;/*

Polycarpus works as a DJ in the best Berland nightclub, and he often uses dubstep music in his performance.
Recently, he has decided to take a couple of old songs and make dubstep remixes from them.

Let's assume that a song consists of some number of words. To make the dubstep remix of this song,
Polycarpus inserts a certain number of words "WUB" before the first word of the song (the number may be zero),
after the last word (the number may be zero), and between words (at least one between any pair of neighbouring words),
and then the boy glues together all the words, including "WUB", in one string and plays the song at the club.

For example, a song with words "I AM X" can transform into a dubstep remix as "WUBWUBIWUBAMWUBWUBX"
and cannot transform into "WUBWUBIAMWUBX".

Recently, Jonny has heard Polycarpus's new dubstep track, but since he isn't into modern music,
he decided to find out what was the initial song that Polycarpus remixed. Help Jonny restore the original song.

Input
The input consists of a single non-empty string, consisting only of uppercase English letters, the string's length
doesn't exceed 200 characters

Output
Return the words of the initial song that Polycarpus used to make a dubsteb remix.
Separate the words with a space.


*/

//WUBWUBABCWUB

public class Dubster {
    public static String songDecoder(String song) {

        String oldSong = "";
        for (int i = 0; i < song.length(); i++) {
           /*
           * String window = song.substring(i, i+2)
           * if(window.equals("WUB"))
           * также, ArrayIndexOutOfBound не за горами при i > song.length() - 3
           * */

            if (song.charAt(i) == 'W' && song.charAt(i + 1) == 'U'
                    && song.charAt(i + 2) == 'B') {
                // StringBuilder как накопитель результата получше, так как не плодит "сиротские"
                // строки в памяти
                oldSong += " ";
                i += 2;
            } else {
                oldSong += song.charAt(i);
            }
        }
        // можно было и в один цикл все уместить, на самом деле
        StringBuilder old = new StringBuilder(oldSong.trim());
        while (old.indexOf(" ") >= 1) {
            if (old.charAt(old.indexOf(" ") + 1) == ' ') {
                old.deleteCharAt(old.indexOf(" "));
            } else {
                break;
            }
        }
        return old.toString();
    }
}
