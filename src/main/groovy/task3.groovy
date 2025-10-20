import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

try {

    def url = 'https://www.gov.br/ans/pt-br'
    println "Acessando: $url"
    Document document = Jsoup.connect(url).get()

    Element linkEspacoPrestador = document.selectFirst("a:contains(Espaço do Prestador)")
    String linkEspacoPrestadorAbsoluto = linkEspacoPrestador.attr("abs:href")
    println "Link do Prestador: $linkEspacoPrestadorAbsoluto"

    Document documentEspacoPrestador = Jsoup.connect(linkEspacoPrestadorAbsoluto).get()
    Element linkTISS = documentEspacoPrestador.selectFirst("a:contains(TISS - Padrão)")
    String linkTISSAbsoluto = linkTISS.attr("abs:href")
    println "Link do TISS (Página Principal): $linkTISSAbsoluto"



    Document documentPadraoTISS = Jsoup.connect(linkTISSAbsoluto).get()

    Element linkTabelas = documentPadraoTISS.selectFirst("a:contains(Clique aqui para acessar as planilhas)")
    String urlTabelasRelacionadas = linkTabelas.attr("abs:href")
    println "Link da Página de Tabelas: $urlTabelasRelacionadas"

    Document documentTabelaDeErros = Jsoup.connect(urlTabelasRelacionadas).get()

    Element linkElement = documentTabelaDeErros.selectFirst("a:contains(tabela de erros no envio para a ANS)")
    String linkDownloadErros = linkElement.attr("abs:href")
    println "Link do Arquivo: $linkDownloadErros"


    def downloadsDir = new File("./Downloads/Tabelas_Relacionadas")
    if (!downloadsDir.exists()) {
        downloadsDir.mkdirs()
        println "Criando diretório: ${downloadsDir.absolutePath}"
    }

    String nomeDoArquivo = linkDownloadErros.split('/')[-1]
    def arquivoLocal = new File(downloadsDir, nomeDoArquivo)
    println "Salvando em: ${arquivoLocal.absolutePath}"


    def urlObj = new URL(linkDownloadErros)
    def conexao = urlObj.openConnection()
    conexao.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")

    arquivoLocal.bytes = conexao.inputStream.bytes

    println "Download da Task 3 concluído!"
    println "Arquivo salvo: ${arquivoLocal.absolutePath}"

} catch (Exception e) {
    println "Ocorreu um erro na Task 3:"
    e.printStackTrace()
}