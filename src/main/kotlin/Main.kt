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


val RPbai = HashMap<Int, Double>()
val BPbai = HashMap<Int, Double>()


fun main(args: Array<String>) {


    go(6, 7, 8, 10, 12, 14)
    go(3,7,14,25,26,27)
    go(5,9,14,17,24,26)
    go(3,4,6,12,13,26)
    go(12,13,23,27,31,33)

}


fun go(r1: Int, r2: Int, r3: Int, r4: Int, r5: Int, r6: Int) {
    RPbai.clear()
    BPbai.clear()
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


    val maxRB = datas.size * 6
    val maxBB = datas.size

    for (index in RP.values.indices) {
        val i = index + 1
        val values = RP.get(i)
        RPbai.put(i, values!!.toDouble() / maxRB.toDouble())
    }

    for (index in BP.values.indices) {
        val i = index + 1
        val values = BP.get(i)
        BPbai.put(i, values!!.toDouble() / maxBB.toDouble())
    }

    println(RPbai)

    var RPLast: Double = 0.00

    var upNum = 0
    var downNum = 0
    var baseP = 0.00
    for (data in datas) {
        var RPNext: Double = 1.00
        for (rb in data.rbs) {
            RPNext *= RPbai.get(rb)!!
        }
        RPNext = (1f / 1107568f) / RPNext
        if (RPLast != 0.00) {
            if (RPNext >= RPLast) {
//                println("上升" + (RPNext - RPLast))
                baseP += (RPNext - RPLast)
                upNum++
            } else {
//                println("下降" + (RPLast - RPNext))
                baseP -= (RPLast - RPNext)
                downNum++
            }
        }
        RPLast = RPNext
        println(RPNext)
    }


    println("上升次数" + upNum)
    println("下降次数" + downNum)
    println(baseP)


    val FWMIN = RPLast - (baseP * (downNum.toDouble() / (upNum + downNum)))
    val FWMAX = RPLast + (baseP * (upNum.toDouble() / (upNum + downNum)))
    println("中奖取值概率范围：" + FWMIN + "到" + FWMAX)
    val num = ArrayList<Int>()
    num.add(r1)
    num.add(r2)
    num.add(r3)
    num.add(r4)
    num.add(r5)
    num.add(r6)
    var fnum = 1.00
    for (n in num) {
        fnum *= RPbai.get(n)!!
    }
    fnum = (1f / 1107568f) / fnum
    println("你的号码范围" + fnum)
}








