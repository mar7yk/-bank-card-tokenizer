import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class DataBase<Data> {
    protected Data data;
    String fileName;

    protected void init(String fileName) {
        this.fileName = fileName;
        try (FileInputStream fos = new FileInputStream(fileName);
             XMLDecoder decoder = new XMLDecoder(fos)
        ) {

            data = (Data) decoder.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             XMLEncoder encoder = new XMLEncoder(fos)
        ) {

            encoder.setExceptionListener(Throwable::printStackTrace);

            encoder.writeObject(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
