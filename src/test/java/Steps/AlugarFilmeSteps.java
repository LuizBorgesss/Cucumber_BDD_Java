package Steps;

import entities.Filme;
import entities.NotaAluguel;
import entities.TipoAluguel;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.AluguelService;
import services.utils.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class AlugarFilmeSteps {

    private Filme filme;
    private AluguelService aluguel = new AluguelService();
    private NotaAluguel nota;
    private String erro;
    private TipoAluguel tipoAluguel = TipoAluguel.COMUM;

    @Dado("um filme com estoque de {int} unidades")
    public void um_filme_com_estoque_de_unidades(Integer int1) {
        filme = new Filme();
        filme.setEstoque(int1);
    }

    @Dado("que o preço do aluguel seja R$ {int}")
    public void que_o_preço_do_aluguel_seja_r$(Integer int1) {
        filme.setAluguel(int1);
    }

    @Dado("um filme")
    public void um_filme(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        filme = new Filme();
        filme.setEstoque(Integer.parseInt(map.get("estoque")));
        filme.setAluguel(Integer.parseInt(map.get("preço")));
    }

    @Quando("alugar")
    public void alugar() {
        try{
            nota = aluguel.alugar(filme, tipoAluguel);
        } catch (RuntimeException e){
            erro = e.getMessage();
        }
    }

    @Então("o preço do alugel será R$ {int}")
    public void o_preço_do_alugel_será_r$(int int1) {
        Assert.assertEquals(int1, nota.getPreco());
    }

    @Então("a data de entrega será no dia seguinte")
    public void a_data_de_entrega_será_no_dia_seguinte() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);

        Date dataRetorno = nota.getDataEntrega();
        Calendar calRetorno = Calendar.getInstance();
        calRetorno.setTime(dataRetorno);

        Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH), calRetorno.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(cal.get(Calendar.MONTH), calRetorno.get(Calendar.MONTH));
        Assert.assertEquals(cal.get(Calendar.YEAR), calRetorno.get(Calendar.YEAR));
    }

    @Então("o estoque do filme será {int} unidade")
    public void o_estoque_do_filme_será_unidade(int int1) {
        Assert.assertEquals(int1, filme.getEstoque());
    }

    @Então("não será possível por falta de estoque")
    public void não_será_possível_por_falta_de_estoque() {
        Assert.assertEquals("Filme sem estoque", erro);
    }

    @Dado("que o tipo do aluguel seja {word}")
    public void que_o_tipo_do_aluguel_seja_extendido(String tipo) {
        tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL: tipo.equals("extendido")? TipoAluguel.EXTENDIDO: TipoAluguel.COMUM;
    }
    @Então("a data de entrega será em {int} dias")
    public void a_data_de_entrega_será_em_dias(Integer int1) {
        Date dataEsperada = DateUtil.obterDataDiferencaDias(int1);
        Date dataReal = nota.getDataEntrega();

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Assert.assertEquals(format.format(dataEsperada),format.format(dataReal));
    }
    @Então("a pontuação será de {int} pontos")
    public void a_pontuação_será_de_pontos(int int1) {
        Assert.assertEquals(int1, nota.getPontuacao());
    }
}
