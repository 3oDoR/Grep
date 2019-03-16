import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrepTest {
    private String abPath = new File("").getPath();
    @Test
    public void noFlags(){
        Grep grep = new Grep();
        try {
            List<String> list = new ArrayList<>();
            list.add("Разнообразный и богатый опыт постоянный количественный" +
                    " рост и сфера нашей активности играет важную роль в формировании " +
                    "дальнейших направлений развитая системы массового участия.");
            assertEquals(list, grep.findLines(new BufferedReader(new FileReader(new File(abPath + "input\\input1.txt"))),false,false,false,"богатый"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FlagI(){
        Grep grep = new Grep();
        try {
            List<String> list = new ArrayList<>();
            list.add("Разнообразный и богатый опыт постоянный количественный рост" +
                    " и сфера нашей активности играет важную роль в формировании" +
                    " дальнейших направлений развитая системы массового участия.");
            assertEquals(list, grep.findLines(new BufferedReader(new FileReader(new File(abPath + "input\\input1.txt"))),true,false,false,"БОГАТЫЙ"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FlagV(){
        Grep grep = new Grep();
        try {
            List<String> list = new ArrayList<>();
            list.add("Значимость этих проблем настолько очевидна, что" +
                    " реализация намеченного плана развития обеспечивает" +
                    " широкому кругу специалистов участие в формировании" +
                    " дальнейших направлений развития проекта.");
            list.add("Повседневная практика показывает, что консультация с профессионалами");
            list.add("123452");
            assertEquals(list, grep.findLines(new BufferedReader(new FileReader(new File(abPath + "input\\input1.txt"))),false,false,true,"богатый"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FlagR(){
        Grep grep = new Grep();
        try {
            List<String> list = new ArrayList<>();
            list.add("123452");
            assertEquals(list, grep.findLines(new BufferedReader(new FileReader(new File(abPath + "input\\input1.txt"))),false,true,false,"\\d"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FlagVI(){
        Grep grep = new Grep();
        try {
            List<String> list = new ArrayList<>();
            list.add("Значимость этих проблем настолько очевидна," +
                    " что реализация намеченного плана развития" +
                    " обеспечивает широкому кругу специалистов" +
                    " участие в формировании дальнейших направлений" +
                    " развития проекта.");
            list.add("Повседневная практика показывает, что консультация с профессионалами");
            list.add("123452");
            assertEquals(list, grep.findLines(new BufferedReader(new FileReader(new File(abPath + "input\\input1.txt"))),true,false,true,"БОГАТЫЙ"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}