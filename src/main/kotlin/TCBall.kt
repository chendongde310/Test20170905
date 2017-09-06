import lombok.Data

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/9/6 - 下午12:06
 * 注释：
 *
 */
@Data
class TCBall {
    var rbs :ArrayList<Int> = ArrayList<Int>()
    var b1 :Int = 0

    override fun toString(): String {
        return b1.toString()
    }
}