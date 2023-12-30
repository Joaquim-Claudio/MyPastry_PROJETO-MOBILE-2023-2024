package pt.iade.mypastry.webserver.results;

public class Response {
    private String msg;
    private Object obj;

    public Response() {
    }
    public Response(String msg, Object obj) {
        this.msg = msg;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public Object getObj() {
        return obj;
    }
}
