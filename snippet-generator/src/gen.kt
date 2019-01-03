import com.google.gson.Gson
import java.io.File

class Item(val prefix: String, val body: Array<String>)

fun l(vararg xs: String): Array<String> = xs.toList().toTypedArray()

fun main(args: Array<String>) {
    val gson = Gson()
    val result = mutableMapOf(
        "update" to Item("_update", l("function _update()", "$0", "end")),
        "update60" to Item("_update60", l("function _update60()", "$0", "end")),
        "init" to Item("_init", l("function _init()", "$0", "end")),
        "draw" to Item("_draw", l("function _draw()", "$0", "end"))
    )

    val generalFuncs = listOf(
        "flip",
        "camera", "circ", "circfill", "clip", "cls", "color", "cursor", "fget",
        "flip", "fset", "line", "pal", "palt", "pget", "print", "pset", "rect",
        "rectfill", "sget", "spr", "sset", "sspr",
        "add", "all", "del", "foreach", "pairs",
        "btn", "btnp",
        "music", "sfx",
        "map", "mget", "mset",
        "cstore", "memcpy", "memset", "peek", "poke", "reload",
        "abs", "atan2", "band", "bnot", "bor", "bxor", "cos", "flr",
        "max", "mid", "min", "rnd", "shl", "shr", "sin", "sqrt", "srand",
        "cartdata", "dget", "dset",
        "cocreate", "coresume", "costatus", "yield",
        "setmetatable", "getmetatable", "type", "sub", "tonum", "tostr",
        "time",
        "menuitem", "extcmd",
        "assert", "printh", "stat"
    )
    for (f in generalFuncs) {
        result += f to Item(f, l("$f($0)"))
    }

    val generalTokens = listOf(
        "function", "local", "begin", "end", "if", "else", "then", "for", "while"
    )
    for (f in generalTokens) {
        result += f to Item(f, l(f));
    }

    File("D:\\test.json").writeText(gson.toJson(result))
}