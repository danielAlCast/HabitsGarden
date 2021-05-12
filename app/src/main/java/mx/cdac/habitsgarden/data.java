package mx.cdac.habitsgarden;

public class data {
    private static final  mx.cdac.habitsgarden.data ourInstance = new mx.cdac.habitsgarden.data();

    public static mx.cdac.habitsgarden.data getInstance() {
        return ourInstance;
    }

    private data() {
    }
}
