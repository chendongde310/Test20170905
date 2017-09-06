import org.dom4j.io.SAXReader
import java.io.File
import java.util.HashMap
import kotlin.collections.ArrayList

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/9/5 - 上午11:03
 * 注释：
 *
 */
fun main(args: Array<String>) {

//创建SAXReader读取器，专门用于读取xml
    val saxReader = SAXReader()
    //根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
    //Document document = saxReader.read(inputStream);
    val document = saxReader.read(File("/Users/chen/IdeaProjects/Test20170905/src/main/kotlin/data.xml"))//必须指定文件的绝对路径
    var datas = ArrayList<TCBall>()
    for (e in document.rootElement.elements("tr")) {
        var TCB = TCBall()
        val rbs = e.elements("td")

        for (rb in rbs) {
            if ("t_cfont2".equals(rb.attributeValue("class"))) {

                TCB.rbs.add(rb.textTrim.toInt())
            }

            if ("t_cfont4".equals(rb.attributeValue("class"))) {

                TCB.b1 = rb.textTrim.toInt()

                break
            }
        }
        datas.add(TCB)
    }

    val RP = HashMap<Int, Int>()
    val BP = HashMap<Int, Int>()
    for (data in datas) {
        for (r in data.rbs) {
            if (RP.containsKey(r)) {
                RP.put(r, RP.get(r)!! + 1)
            } else {
                RP.put(r, 1)
            }

        }

        if (BP.containsKey(data.b1)) {
            BP.put(data.b1, BP.get(data.b1)!! + 1)
        } else {
            BP.put(data.b1, 1)
        }

    }



    val maxRB  = datas.size*6
    val maxBB  = datas.size
    val RPbai = HashMap<Int, Double>()
    val BPbai = HashMap<Int, Double>()
    for (index in RP.values.indices){
        val i = index+1
        val  values  = RP.get(i)
        RPbai.put(i, values!!.toDouble()/maxRB.toDouble())
    }

    for (index in BP.values.indices){
        val i = index+1
        val  values  = BP.get(i)
        BPbai.put(i, values!!.toDouble()/maxBB.toDouble())
    }








    for (data in datas){
        var RPAll : Double = 1.00
        for (rb in data.rbs){
            RPAll *= RPbai.get(rb)!!
        }
//        RPAll = RPAll*(1/36)

    }




}





