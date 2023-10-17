package Steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AprenderCucumberSteps {

    private Calendar dataEntrega = Calendar.getInstance();
    private DateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    @Dado("que a entrega é dia {int}\\/{int}\\/{int}")
    public void queAEntregaÉDia(int dia, int mes, int ano) {

        dataEntrega.set(Calendar.DAY_OF_MONTH, dia);
        dataEntrega.set(Calendar.MONTH, mes-1);
        dataEntrega.set(Calendar.YEAR, ano);

        date.format(dataEntrega.getTime());
    }

    @Quando("a entrega atrasar em {int} {word}")
    public void aEntregaAtrasarEmDias(int periodo, String tempo) {
        if (tempo.equals("dia") || tempo.equals("dias")){
            dataEntrega.add(Calendar.DAY_OF_MONTH, periodo);
        }
        if (tempo.equals("mês") || tempo.equals("meses")){
            dataEntrega.add(Calendar.MONTH, periodo);
        }
        date.format(dataEntrega.getTime());
    }

    @Então("a entrega será efetuada em {int}\\/{int}\\/{int}")
    public void aEntregaSeráEfetuadaEm(int dia, int mes, int ano) {
       Calendar novaDataEntrega = Calendar.getInstance();

       novaDataEntrega.set(Calendar.DAY_OF_MONTH, dia);
       novaDataEntrega.set(Calendar.MONTH, mes - 1);
       novaDataEntrega.set(Calendar.YEAR, ano);

        date.format(novaDataEntrega.getTime());
        date.format(dataEntrega.getTime());

        Assert.assertEquals(
                date.format(novaDataEntrega.getTime()),
                date.format(dataEntrega.getTime())
        );
    }
    int pontos = 0;
    @Dado("que os pontos antes da vitória são {int}")
    public void que_os_pontos_antes_da_vitória_são(Integer int1) {
        pontos = int1;
    }
    @Quando("ganhar {int} pontos pela vitória")
    public void ganhar_pontos_pela_vitória(Integer int1) {
        pontos = pontos + int1;
    }
    @Entao("a quantidade de pontos será {int}")
    public void a_quantidade_de_pontos_será(int int1) {
        System.out.println(int1);
        System.out.println(pontos);
        Assert.assertEquals(int1, pontos);
    }



}


