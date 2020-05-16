package cl.web.community;

public class MyResult {
    private int code;
    private String message;

    public MyResult(int code,String message){
        this.code =code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
