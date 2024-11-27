package ir.linuxian.wwd.tables;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "loqat")
public class Loqat {


    @PrimaryKey
    //@NonNull
    Integer id;

    @ColumnInfo
    String word;

    @ColumnInfo
    String comment;

    public Loqat(int id, String word, String comment) {
        this.id = id;
        this.word = word;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getComment() {
        return comment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
