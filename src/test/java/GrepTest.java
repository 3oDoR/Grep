import org.junit.jupiter.api.Test;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrepTest {

    @Test
    void noFlags(){
        Grep grep = new Grep(false,false,false,"богатый","input\\input1.txt");
        List<String> list = new ArrayList<>();
        list.add("Разнообразный и богатый опыт постоянный количественный рост и сфера нашей активности играет важную роль в формировании дальнейших направлений развитая системы массового участия.");
        assertEquals(list, grep.grep());
    }


    @Test
    void FlagI(){
        Grep grep = new Grep(false,true,false,"БОГАТЫЙ","input\\input1.txt");
            List<String> list = new ArrayList<>();
            list.add("Разнообразный и богатый опыт постоянный количественный рост и сфера нашей активности играет важную роль в формировании дальнейших направлений развитая системы массового участия.");
            assertEquals(list, grep.grep());
    }

    @Test
    void FlagV(){
        Grep grep = new Grep(false,false,true,"богатый","input\\input1.txt");
            List<String> list = new ArrayList<>();
            list.add("Значимость этих проблем настолько очевидна, что" +
                    " реализация намеченного плана развития обеспечивает" +
                    " широкому кругу специалистов участие в формировании" +
                    " дальнейших направлений развития проекта.");
            list.add("Повседневная практика показывает, что консультация с профессионалами");
            list.add("123452");
            assertEquals(list, grep.grep());

    }

    @Test
    void FlagR(){
        Grep grep = new Grep(true,false,false,"\\d","input\\input1.txt");
            List<String> list = new ArrayList<>();
            list.add("123452");
            assertEquals(list, grep.grep());

    }

    @Test
    void FlagVI(){
        Grep grep = new Grep(false,true,true,"БОГАТЫЙ","input\\input1.txt");
            List<String> list = new ArrayList<>();
            list.add("Значимость этих проблем настолько очевидна," +
                    " что реализация намеченного плана развития" +
                    " обеспечивает широкому кругу специалистов" +
                    " участие в формировании дальнейших направлений" +
                    " развития проекта.");
            list.add("Повседневная практика показывает, что консультация с профессионалами");
            list.add("123452");
            assertEquals(list, grep.grep());


    }

}