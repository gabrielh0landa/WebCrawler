import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

def url = 'https://pt.wikipedia.org/wiki/Wikip%C3%A9dia:P%C3%A1gina_principal'

Document document = Jsoup.connect(url).get()

Element titulo = document.selectFirst('h1')

String textoTitulo = titulo.text()

println "Título da página da Wikipedia: ${textoTitulo}"