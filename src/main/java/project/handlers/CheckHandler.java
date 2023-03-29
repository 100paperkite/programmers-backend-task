package project.handlers;


import project.dto.Check;

public class CheckHandler extends JsonHttpHandler {

    @Override
    public Check response() {
        return new Check("server check");
    }
}
