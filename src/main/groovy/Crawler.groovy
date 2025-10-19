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
    println "Link do TISS: $linkTISSAbsoluto"

    Document documentPadraoTISS = Jsoup.connect(linkTISSAbsoluto).get()
    Element PadraoLinkTISS = documentPadraoTISS.selectFirst("a:contains(Clique aqui para acessar)")
    String PadraoLinkTISSAbsoluto = PadraoLinkTISS.attr("abs:href")
    println "Link da Página de Download: $PadraoLinkTISSAbsoluto"

    Document documentTabelaDeDocumentos = Jsoup.connect(PadraoLinkTISSAbsoluto).get()
    Element htmltabelaDeDocumentos = documentTabelaDeDocumentos.selectFirst("table")
    Element htmlLinkDocumento = documentTabelaDeDocumentos.selectFirst("a:contains(Componente de Comunicação.)")
    String textohtmlLinkDocumento = htmlLinkDocumento.attr("abs:href")
    println "Texto Link: $textohtmlLinkDocumento"
    String textohtmltabelaDeDocumentos = htmltabelaDeDocumentos.outerHtml()
    println "Tabela de Elementos: $textohtmltabelaDeDocumentos"


    def downloadsDir = new File("./Downloads/Aquivos_padrao_TISS")
    if (!downloadsDir.exists()) {
        downloadsDir.mkdirs()
    }

    String nomeDoArquivo = textohtmlLinkDocumento.split('/')[-1]
    def arquivoLocal = new File(downloadsDir, nomeDoArquivo)

    def urlObj = new URL(textohtmlLinkDocumento)
    def conexao = urlObj.openConnection()
    conexao.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")

    arquivoLocal.bytes = conexao.inputStream.bytes

    println "Download concluído!"
    println "Arquivo salvo: ${arquivoLocal.absolutePath}"

} catch (Exception e) {
    println "Ocorreu um erro:"
    e.printStackTrace()
}