package gtech.util;

import java.util.ArrayList;

public class CloseableList<T extends AutoCloseable> implements AutoCloseable {
    private final ArrayList<AutoCloseable> list = new ArrayList<>();

    /**
     * Add resource to the list.
     * @param closeable the resource, must not be null.
     */
    public void add(T closeable) {
        if (closeable == null) {
            throw new NullPointerException("closeable");
        }
        list.add(closeable);
    }

    /**
     * Remove resource from the list, does not close it.
     * @param closeable resource to remove
     * @return true if resource was in the list.
     */
    public boolean remove(T closeable) {
        return list.remove(closeable);
    }

    /**
     * Remove resource from the list and close it.
     * @param closeable resource to remove and close
     * @return true if resource was in the list
     */
    public boolean removeAndClose(T closeable) {
        if (list.remove(closeable)) {
            try {
                closeable.close();
            } catch (Exception ignored) {
            }
            return true;
        }
        return false;
    }

    /**
     * Close all resources in the list.
     */
    @Override
    public void close() {
        for (AutoCloseable closeable : list) {
            try {
                closeable.close();
            } catch (Exception ignored) {
            }
        }
    }
}
