package Questions.StackOverflowDesign;

import java.util.UUID;

public class Tag {
  private final  UUID tagId;
  private final   String name;

    public Tag( String name) {
        this.tagId = UUID.randomUUID();
        this.name = name;
    }

    public UUID getTagId() {
        return UUID.randomUUID();
    }

    public String getName() {
        return name;
    }
}
