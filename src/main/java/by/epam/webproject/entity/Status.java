package by.epam.webproject.entity;

/**
 * Status shows in what conditions Order can be
 */

public enum Status {
    ;
   /* NEW(1, Status.NEW.name()), EXECUTE(2, Status.EXECUTE.name())
    , READY(3, Status.READY.name()), CLOSE(4, Status.CLOSE.name());
*/
    private int id;
    private String name;

    Status() {
    }

    Status(String name) {
        this.name = name;
    }

    Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }
}
