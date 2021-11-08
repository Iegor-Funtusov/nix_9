package ua.com.alevel.generics;

public class ResponseContainer<DATA, STATUS> {

    private final DATA data;
    private final STATUS status;

    public ResponseContainer(DATA data, STATUS status) {
        this.data = data;
        this.status = status;
    }

    public DATA getData() {
        return data;
    }

    public STATUS getStatus() {
        return status;
    }
}
