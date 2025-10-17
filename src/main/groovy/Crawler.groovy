import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

def url = 'https://www.gov.br/ans/pt-br'

Document document = Jsoup.connect(url).get()
Element linkEspacoPrestador = document.selectFirst("a:contains(Espaço do Prestador)")
String linkEspacoPrestadorAbsoluto  = linkEspacoPrestador.attr("abs:href")
String textoLinkEspacoPrestador = linkEspacoPrestador.text()

Document documentEspacoPrestador = Jsoup.connect(linkEspacoPrestadorAbsoluto).get()
Element linkTISS = documentEspacoPrestador.selectFirst("a:contains(TISS - Padrão)")
String linkTISSAbsoluto = linkTISS.attr("abs:href")
String textoLinkTISS = linkTISS.text() 

Document documentPadraoTISS = Jsoup.connect(linkTISSAbsoluto).get()
Element PadraoLinkTISS = documentPadraoTISS.selectFirst("a:contains(Clique aqui para acessar)")
String PadraoLinkTISSAbsoluto = PadraoLinkTISS.attr("abs:href")
String textoPadraoLinkTISS = PadraoLinkTISS.text()

println "$textoLinkEspacoPrestador" 
println "$linkEspacoPrestadorAbsoluto" 

println "$textoLinkTISS"
println "$linkTISSAbsoluto"

println "$textoPadraoLinkTISS"
println "$PadraoLinkTISSAbsoluto"
