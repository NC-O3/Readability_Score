package readability;

public class FleschKincaidIndex {

    private int numberOfWords;
    private int numberOfSentences;
    private int numberOfSyllables;
    private double score;
    private String age;
    private double ageDouble;

    public FleschKincaidIndex(int numberOfWords, int numberOfSentences, int numberOfSyllables) {
        this.numberOfWords = numberOfWords;
        this.numberOfSentences = numberOfSentences;
        this.numberOfSyllables = numberOfSyllables;
        determineScore();
        determineAge();
    }

    private void determineScore() {
        score = 0.39 * numberOfWords / numberOfSentences +
                11.8 * numberOfSyllables / numberOfWords - 15.59;
    }

    private void determineAge() {
        int scoreInteger = (int) score;

        switch (scoreInteger) {
            case 1:
                age = "7-year-olds";
                ageDouble = 7;
                break;
            case 2:
                age = "8-year-olds";
                ageDouble = 8;
                break;
            case 3:
                age = "9-year-olds";
                ageDouble = 9;
                break;
            case 4:
                age = "10-year-olds";
                ageDouble = 10;
                break;
            case 5:
                age = "11-year-olds";
                ageDouble = 11;
                break;
            case 6:
                age = "12-year-olds";
                ageDouble = 12;
                break;
            case 7:
                age = "13-year-olds";
                ageDouble = 13;
                break;
            case 8:
                age = "14-year-olds";
                break;
            case 9:
                age = "15-year-olds";
                ageDouble = 15;
                break;
            case 10:
                age = "16-year-olds";
                ageDouble = 16;
                break;
            case 11:
                age = "17-year-olds";
                ageDouble = 17;
                break;
            case 12:
                age = "18-year-olds";
                ageDouble = 18;
                break;
            case 13:
                age = "19-year-olds";
                ageDouble = 19;
                break;
            case 14:
                age = "20-year-olds";
                ageDouble = 20;
                break;
            case 15:
                age = "21-year-olds";
                ageDouble = 21;
                break;
            case 16:
                age = "22-year-olds";
                ageDouble = 22;
                break;
            case 17:
                age = "22+-year-olds";
                ageDouble = 23;
                break;
        }
    }

    public String getAge() {
        return age;
    }

    public String getScore() {
        return String.format("%.2f", score);
    }

    public double getAgeDouble() {
        return ageDouble;
    }
}
