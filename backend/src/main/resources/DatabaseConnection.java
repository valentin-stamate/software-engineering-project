package resources;


@Entity
@Table(name = "database")
public class DatabaseConnection{
    private Integer connectionId;

    public DatabaseConnection(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public void close(){
        closeConnection(connectionId);
    }

    public void open(String url){
        openConnection(connectionId);
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "connectionId=" + connectionId +
                '}';
    }
}