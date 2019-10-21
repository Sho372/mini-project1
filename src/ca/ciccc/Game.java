package ca.ciccc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

  private String answer;
  private String hiddenAnswer;
  private int numMistake;
  private ArrayList<String> guessedLetters;
  private boolean isFinished;

  private final int PLAYER_POINTS = 10;

  public Game() {
    setAnswer(FileReader.getAnswer());
    setNumMistake(0);

    ArrayList<String> arr = new ArrayList<>();
    arr.add(" ");

    setGuessedLetters(arr);
    setHiddenAnswer(hideAnswer());
    setFinished(false);
  }

  private void addGuess(String guess) {
    this.guessedLetters.add(guess);
  }

  private String hideAnswer() {
    String str = "";
    String[] answerArr = this.answer.split("");
    for (String c : answerArr) {
      if (this.guessedLetters.contains(c)) {
        str += c;
      } else {
        str += "_";
      }
    }
    return str;
  }

  private ArrayList<String> getWrongLetters() {
    String[] answerArr = this.answer.split("");
    // copy
    // https://stackoverflow.com/questions/6536094/java-arraylist-copy
    ArrayList<String> copy = new ArrayList<>(this.guessedLetters);
    copy.remove(" ");
    for (String c : answerArr) {
      if (copy.contains(c)) {
        // remove
        copy.remove(c);
      }
    }
    return copy;
  }

  private String getWrongLettersString() {
    ArrayList<String> wrongLetters = getWrongLetters();
    String str = "";
    for (String c : wrongLetters) {
      str += (c + " ");
    }
    return str;
  }

  // #1
  public boolean isDuplicatedGuess(String guess) {
    return guessedLetters.contains(guess);
  }

  // #2
  public void updateGame(String guess) {
    addGuess(guess);
    setHiddenAnswer(hideAnswer());
    if (!isCorrectGuess(guess)) {
      this.numMistake += 1;
    }
  }

  private boolean isCorrectGuess(String guess) {
    // https://stackoverflow.com/questions/9105358/how-can-i-verify-that-an-array-of-strings-contain-a-certain-string
    String[] answerArr = this.answer.split("");
    List answerList = Arrays.asList(answerArr);
    return answerList.contains(guess);
  }

  // #3
  // call after updateGame method, it needs adding guess to guessedLetters
  private boolean isWin() {
    String[] answerArr = this.answer.split("");
    for (String c : answerArr) {
      if (!this.guessedLetters.contains(c)) {
        return false;
      }
    }
    return true;
  }

  // #4
  public void displayResult() {
    if (isWin()) {
      System.out.println("You win!");
      System.out.println("You have guessed '" + this.answer + "' correctly.");
      setFinished(true);
    } else {
      System.out.println("You are guessing: " + this.hiddenAnswer);
      System.out.println(
          "You have guessed (" + this.numMistake + ") wrong letters: " + getWrongLettersString());
      if (this.numMistake == PLAYER_POINTS) {
        System.out.println("You lose!");
        System.out.println("The correct word was '" + this.answer + "'!");
        setFinished(true);
      }
    }
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public void setNumMistake(int numMistake) {
    this.numMistake = numMistake;
  }

  public void setGuessedLetters(ArrayList<String> guessedLetters) {
    this.guessedLetters = guessedLetters;
  }

  public boolean isFinished() {
    return this.isFinished;
  }

  public void setFinished(boolean finished) {
    this.isFinished = finished;
  }

  public String getHiddenAnswer() {
    return this.hiddenAnswer;
  }

  public void setHiddenAnswer(String hiddenAnswer) {
    this.hiddenAnswer = hiddenAnswer;
  }
}
