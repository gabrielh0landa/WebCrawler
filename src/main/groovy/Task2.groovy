import org.jsoup.Jsoup

try {
    def url = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-historico-das-versoes-dos-componentes-do-padrao-tiss'

    def docHistorico = Jsoup.connect(url).get()

    def tabelaHistorico = docHistorico.selectFirst("table")

    println "Tabela encontrada. Coletando dados..."

    println "COMPETÊNCIA | PUBLICAÇÃO | INÍCIO VIGÊNCIA"

    boolean paradaEncontrada = false

    for (linha in tabelaHistorico.select("tbody tr")) {
        def colunas = linha.select("td")

        if (colunas.size() < 3) continue

        def competencia = colunas[0].text()
        def publicacao = colunas[1].text()
        def vigencia = colunas[2].text()

        println "$competencia | $publicacao | $vigencia"

        if (competencia.equalsIgnoreCase("jan/2016")) {
            println "----------------------------------------"
            println "Limite 'jan/2016' encontrado."
            paradaEncontrada = true
            break
        }
    }

} catch (Exception e) {
    println "Falha ao executar: ${e.message}"
}