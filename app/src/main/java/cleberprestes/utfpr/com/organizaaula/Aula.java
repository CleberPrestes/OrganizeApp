package cleberprestes.utfpr.com.organizaaula;

import android.graphics.drawable.Drawable;

public class Aula {

    public static final int MANHA     = 1;
    public static final int TARDE     = 2;
    public static final int NOITE = 3;

   private String dia;
   private String periodo;
   private String aula;
   private String disciplina;

    public Aula(String disciplina, String dia, String periodo, String aula) {
        this.dia = dia;
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.aula = aula;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }


    @Override
    public String toString() {
        return dia + " - " +
                periodo + " - " +
                aula + " - " +
                disciplina ;
    }

    public int getNumbersImageId() {
        return 0;
    }
}
