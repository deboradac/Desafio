import scala.io.Source
import scala.collection._
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URLConnection
import java.io.InputStream

class StopWords {

  override def toString = lista.toString
  
  val lista = getStopWords()

  def getStopWords():List[String] = {
    var line : String = "";
    val listMutable = mutable.ListBuffer[String]();
    //Acessar URL
    val url: URL = new URL("http://members.unine.ch/jacques.savoy/clef/englishST.txt")
    val conn: URLConnection  = url.openConnection(); 
    val is: InputStream = url.openStream(); 
    val isr: InputStreamReader = new InputStreamReader(is); 
    val br: BufferedReader = new BufferedReader(isr); 
    
    //Obter o primeiro elemento
    line = br.readLine();
    //Obter demais elementos
    while (line != null) {
      if (line.length() > 1){
        listMutable += line;
      }
      line = br.readLine();
    }
    
    //Retornar lista com objetos com mais de uma letra
    val lista = listMutable.toList;   //lista.foreach(println)
    return lista;
  }

  def part (c: String) : (List[String], List[String]) = {
    //Retorna duas listas
    return lista.partition (_.indexOf(c) > -1)
  }
}
  
object StopWordsObj {
  def main(args: Array[String]) {
    val spwords = new StopWords()
    val (contracao, semcontracao) = (spwords.part("'"))
    System.out.println("Palavras com contracao: " + contracao.size);
    System.out.println("Palavras sem contracao: " + semcontracao.size);
  }
}