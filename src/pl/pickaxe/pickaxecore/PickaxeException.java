package pl.pickaxe.pickaxecore;

public class PickaxeException extends Exception {
  private static final long serialVersionUID = -2316812746861540202L;

  public PickaxeException() {
    super();
  }

  public PickaxeException(String message) {
    super(message);
  }

  public PickaxeException(String message, Throwable cause) {
    super(message, cause);
  }

  public PickaxeException(Throwable cause) {
    super(cause);
  }

  public void hide(Throwable cause) {
    PickaxeCore.get().log("Testsstop");
    cause.printStackTrace();
  }
}
