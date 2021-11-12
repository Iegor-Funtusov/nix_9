package ua.com.alevel;

public class Container<DATA> {

    DATA data;

    public DATA getData() {
        return data;
    }

    public void setData(DATA data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Container{" +
                "data=" + data +
                '}';
    }
}
