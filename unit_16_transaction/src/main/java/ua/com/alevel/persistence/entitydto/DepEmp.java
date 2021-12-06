package ua.com.alevel.persistence.entitydto;

public record DepEmp(long jsCount, long javaCount) {

    public long getJsCount() {
        return jsCount;
    }

    public long getJavaCount() {
        return javaCount;
    }

    @Override
    public String toString() {
        return "DepEmp{" +
                "jsCount=" + jsCount +
                ", javaCount=" + javaCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepEmp depEmp = (DepEmp) o;

        if (jsCount != depEmp.jsCount) return false;
        return javaCount == depEmp.javaCount;
    }

    @Override
    public int hashCode() {
        int result = (int) (jsCount ^ (jsCount >>> 32));
        result = 31 * result + (int) (javaCount ^ (javaCount >>> 32));
        return result;
    }
}
