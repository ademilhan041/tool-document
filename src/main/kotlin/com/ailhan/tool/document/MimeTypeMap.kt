package com.ailhan.tool.document

import java.util.regex.Pattern

object MimeTypeMap {
    private val toExtMap: HashMap<String, String> = HashMap()
    private val toMimeTypeMap: HashMap<String, String> = HashMap()

    init {
        load()
    }

    fun hasMimeType(mimeType: String?): Boolean {
        return if (mimeType != null && mimeType.isNotEmpty()) {
            toExtMap.containsKey(mimeType)
        } else false
    }

    fun hasNotMimeType(mimeType: String?): Boolean {
        return !hasMimeType(mimeType)
    }

    fun mimeTypeFromExtension(extension: String?): String? {
        return if (extension != null && extension.isNotEmpty()) {
            toMimeTypeMap[extension]
        } else null
    }

    fun hasExtension(extension: String?): Boolean {
        return if (extension != null && extension.isNotEmpty()) {
            toMimeTypeMap.containsKey(extension)
        } else false
    }

    fun extensionFromMimeType(mimeType: String?): String? {
        return if (mimeType != null && mimeType.isNotEmpty()) {
            toExtMap[mimeType]
        } else null
    }

    fun extensionFromUrl(from: String?): String {
        var url = from
        if (url != null && url.isNotEmpty()) {
            val query = url.lastIndexOf('?')
            if (query > 0) {
                url = url.substring(0, query)
            }
            val filenamePos = url.lastIndexOf('/')
            val filename = if (0 <= filenamePos) url.substring(filenamePos + 1) else url
            if (filename.isNotEmpty() && Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", filename)) {
                val dotPos = filename.lastIndexOf('.')
                if (0 <= dotPos) {
                    return filename.substring(dotPos + 1)
                }
            }
        }
        return ""
    }

    fun mimeTypeFromUrl(from: String?): String {
        val extension = extensionFromUrl(from)
        if (extension.isEmpty()) return ""
        return mimeTypeFromExtension(extension) ?: ""
    }

    fun getImageMimeTypes() = arrayOf(
        "image/bmp",
        "image/gif",
        "image/ico",
        "image/ico",
        "image/ief",
        "image/jpeg",
        "image/pcx",
        "image/png",
        "image/svg+xml",
        "image/svg+xml",
        "image/tiff",
        "image/tiff",
        "image/vnd.djvu",
        "image/vnd.djvu",
        "image/vnd.wap.wbmp",
        "image/x-cmu-raster",
        "image/x-coreldraw",
        "image/x-coreldrawpattern",
        "image/x-coreldrawtemplate",
        "image/x-corelphotopaint",
        "image/x-icon",
        "image/x-jg",
        "image/x-jng",
        "image/x-ms-bmp",
        "image/x-photoshop",
        "image/x-portable-anymap",
        "image/x-portable-bitmap",
        "image/x-portable-graymap",
        "image/x-portable-pixmap",
        "image/x-rgb",
        "image/x-xbitmap",
        "image/x-xpixmap",
        "image/x-xwindowdump"
    )

    fun getVideoMimeTypes() = arrayOf(
        "video/3gpp",
        "video/3gpp",
        "video/3gpp",
        "video/dl",
        "video/dv",
        "video/dv",
        "video/fli",
        "video/m4v",
        "video/mpeg",
        "video/mpeg",
        "video/mpeg",
        "video/mp4",
        "video/mpeg",
        "video/quicktime",
        "video/quicktime",
        "video/vnd.mpegurl",
        "video/x-la-asf",
        "video/x-la-asf",
        "video/x-mng",
        "video/x-ms-asf",
        "video/x-ms-asf",
        "video/x-ms-wm",
        "video/x-ms-wmv",
        "video/x-ms-wmx",
        "video/x-ms-wvx",
        "video/x-msvideo",
        "video/x-sgi-movie"
    )

    private fun loadEntry(mimeType: String, extension: String) {
        if (!toExtMap.containsKey(mimeType)) {
            toExtMap[mimeType] = extension
        }
        toMimeTypeMap[extension] = mimeType
    }

    private fun load() {
        loadEntry("application/andrew-inset", "ez")
        loadEntry("application/dsptype", "tsp")
        loadEntry("application/futuresplash", "spl")
        loadEntry("application/hta", "hta")
        loadEntry("application/json", "json")
        loadEntry("application/mac-binhex40", "hqx")
        loadEntry("application/mac-compactpro", "cpt")
        loadEntry("application/mathematica", "nb")
        loadEntry("application/msaccess", "mdb")
        loadEntry("application/oda", "oda")
        loadEntry("application/ogg", "ogg")
        loadEntry("application/pdf", "pdf")
        loadEntry("application/pgp-keys", "key")
        loadEntry("application/pgp-signature", "pgp")
        loadEntry("application/pics-rules", "prf")
        loadEntry("application/rar", "rar")
        loadEntry("application/rdf+xml", "rdf")
        loadEntry("application/rss+xml", "rss")
        loadEntry("application/zip", "zip")
        loadEntry("application/vnd.android.package-archive", "apk")
        loadEntry("application/vnd.cinderella", "cdy")
        loadEntry("application/vnd.ms-pki.stl", "stl")
        loadEntry("application/vnd.oasis.opendocument.database", "odb")
        loadEntry("application/vnd.oasis.opendocument.formula", "odf")
        loadEntry("application/vnd.oasis.opendocument.graphics", "odg")
        loadEntry("application/vnd.oasis.opendocument.graphics-template", "otg")
        loadEntry("application/vnd.oasis.opendocument.image", "odi")
        loadEntry("application/vnd.oasis.opendocument.spreadsheet", "ods")
        loadEntry("application/vnd.oasis.opendocument.spreadsheet-template", "ots")
        loadEntry("application/vnd.oasis.opendocument.text", "odt")
        loadEntry("application/vnd.oasis.opendocument.text-master", "odm")
        loadEntry("application/vnd.oasis.opendocument.text-template", "ott")
        loadEntry("application/vnd.oasis.opendocument.text-web", "oth")
        loadEntry("application/msword", "doc")
        loadEntry("application/msword", "dot")
        loadEntry("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx")
        loadEntry("application/vnd.openxmlformats-officedocument.wordprocessingml.template", "dotx")
        loadEntry("application/vnd.ms-excel", "xls")
        loadEntry("application/vnd.ms-excel", "xlt")
        loadEntry("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx")
        loadEntry("application/vnd.openxmlformats-officedocument.spreadsheetml.template", "xltx")
        loadEntry("application/vnd.ms-powerpoint", "ppt")
        loadEntry("application/vnd.ms-powerpoint", "pot")
        loadEntry("application/vnd.ms-powerpoint", "pps")
        loadEntry("application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx")
        loadEntry("application/vnd.openxmlformats-officedocument.presentationml.template", "potx")
        loadEntry("application/vnd.openxmlformats-officedocument.presentationml.slideshow", "ppsx")
        loadEntry("application/vnd.rim.cod", "cod")
        loadEntry("application/vnd.smaf", "mmf")
        loadEntry("application/vnd.stardivision.calc", "sdc")
        loadEntry("application/vnd.stardivision.draw", "sda")
        loadEntry("application/vnd.stardivision.impress", "sdd")
        loadEntry("application/vnd.stardivision.impress", "sdp")
        loadEntry("application/vnd.stardivision.math", "smf")
        loadEntry("application/vnd.stardivision.writer", "sdw")
        loadEntry("application/vnd.stardivision.writer", "vor")
        loadEntry("application/vnd.stardivision.writer-global", "sgl")
        loadEntry("application/vnd.sun.xml.calc", "sxc")
        loadEntry("application/vnd.sun.xml.calc.template", "stc")
        loadEntry("application/vnd.sun.xml.draw", "sxd")
        loadEntry("application/vnd.sun.xml.draw.template", "std")
        loadEntry("application/vnd.sun.xml.impress", "sxi")
        loadEntry("application/vnd.sun.xml.impress.template", "sti")
        loadEntry("application/vnd.sun.xml.math", "sxm")
        loadEntry("application/vnd.sun.xml.writer", "sxw")
        loadEntry("application/vnd.sun.xml.writer.global", "sxg")
        loadEntry("application/vnd.sun.xml.writer.template", "stw")
        loadEntry("application/vnd.visio", "vsd")
        loadEntry("application/x-abiword", "abw")
        loadEntry("application/x-apple-diskimage", "dmg")
        loadEntry("application/x-bcpio", "bcpio")
        loadEntry("application/x-bittorrent", "torrent")
        loadEntry("application/x-cdf", "cdf")
        loadEntry("application/x-cdlink", "vcd")
        loadEntry("application/x-chess-pgn", "pgn")
        loadEntry("application/x-cpio", "cpio")
        loadEntry("application/x-debian-package", "deb")
        loadEntry("application/x-debian-package", "udeb")
        loadEntry("application/x-director", "dcr")
        loadEntry("application/x-director", "dir")
        loadEntry("application/x-director", "dxr")
        loadEntry("application/x-dms", "dms")
        loadEntry("application/x-doom", "wad")
        loadEntry("application/x-dvi", "dvi")
        loadEntry("application/x-flac", "flac")
        loadEntry("application/x-font", "pfa")
        loadEntry("application/x-font", "pfb")
        loadEntry("application/x-font", "gsf")
        loadEntry("application/x-font", "pcf")
        loadEntry("application/x-font", "pcf.Z")
        loadEntry("application/x-freemind", "mm")
        loadEntry("application/x-futuresplash", "spl")
        loadEntry("application/x-gnumeric", "gnumeric")
        loadEntry("application/x-go-sgf", "sgf")
        loadEntry("application/x-graphing-calculator", "gcf")
        loadEntry("application/x-gtar", "gtar")
        loadEntry("application/x-gtar", "tgz")
        loadEntry("application/x-gtar", "taz")
        loadEntry("application/x-hdf", "hdf")
        loadEntry("application/x-ica", "ica")
        loadEntry("application/x-internet-signup", "ins")
        loadEntry("application/x-internet-signup", "isp")
        loadEntry("application/x-iphone", "iii")
        loadEntry("application/x-iso9660-image", "iso")
        loadEntry("application/x-jmol", "jmz")
        loadEntry("application/x-kchart", "chrt")
        loadEntry("application/x-killustrator", "kil")
        loadEntry("application/x-koan", "skp")
        loadEntry("application/x-koan", "skd")
        loadEntry("application/x-koan", "skt")
        loadEntry("application/x-koan", "skm")
        loadEntry("application/x-kpresenter", "kpr")
        loadEntry("application/x-kpresenter", "kpt")
        loadEntry("application/x-kspread", "ksp")
        loadEntry("application/x-kword", "kwd")
        loadEntry("application/x-kword", "kwt")
        loadEntry("application/x-latex", "latex")
        loadEntry("application/x-lha", "lha")
        loadEntry("application/x-lzh", "lzh")
        loadEntry("application/x-lzx", "lzx")
        loadEntry("application/x-maker", "frm")
        loadEntry("application/x-maker", "maker")
        loadEntry("application/x-maker", "frame")
        loadEntry("application/x-maker", "fb")
        loadEntry("application/x-maker", "book")
        loadEntry("application/x-maker", "fbdoc")
        loadEntry("application/x-mif", "mif")
        loadEntry("application/x-ms-wmd", "wmd")
        loadEntry("application/x-ms-wmz", "wmz")
        loadEntry("application/x-msi", "msi")
        loadEntry("application/x-ns-proxy-autoconfig", "pac")
        loadEntry("application/x-nwc", "nwc")
        loadEntry("application/x-object", "o")
        loadEntry("application/x-oz-application", "oza")
        loadEntry("application/x-pkcs12", "p12")
        loadEntry("application/x-pkcs7-certreqresp", "p7r")
        loadEntry("application/x-pkcs7-crl", "crl")
        loadEntry("application/x-quicktimeplayer", "qtl")
        loadEntry("application/x-shar", "shar")
        loadEntry("application/x-shockwave-flash", "swf")
        loadEntry("application/x-stuffit", "sit")
        loadEntry("application/x-sv4cpio", "sv4cpio")
        loadEntry("application/x-sv4crc", "sv4crc")
        loadEntry("application/x-tar", "tar")
        loadEntry("application/x-texinfo", "texinfo")
        loadEntry("application/x-texinfo", "texi")
        loadEntry("application/x-troff", "t")
        loadEntry("application/x-troff", "roff")
        loadEntry("application/x-troff-man", "man")
        loadEntry("application/x-ustar", "ustar")
        loadEntry("application/x-wais-source", "src")
        loadEntry("application/x-wingz", "wz")
        loadEntry("application/x-webarchive", "webarchive")
        loadEntry("application/x-x509-ca-cert", "crt")
        loadEntry("application/x-x509-user-cert", "crt")
        loadEntry("application/x-xcf", "xcf")
        loadEntry("application/x-xfig", "fig")
        loadEntry("application/xhtml+xml", "xhtml")
        loadEntry("audio/3gpp", "3gpp")
        loadEntry("audio/basic", "snd")
        loadEntry("audio/midi", "mid")
        loadEntry("audio/midi", "midi")
        loadEntry("audio/midi", "kar")
        loadEntry("audio/mpeg", "mpga")
        loadEntry("audio/mpeg", "mpega")
        loadEntry("audio/mpeg", "mp2")
        loadEntry("audio/mpeg", "mp3")
        loadEntry("audio/mpeg", "m4a")
        loadEntry("audio/mpegurl", "m3u")
        loadEntry("audio/prs.sid", "sid")
        loadEntry("audio/x-aiff", "aif")
        loadEntry("audio/x-aiff", "aiff")
        loadEntry("audio/x-aiff", "aifc")
        loadEntry("audio/x-gsm", "gsm")
        loadEntry("audio/x-mpegurl", "m3u")
        loadEntry("audio/x-ms-wma", "wma")
        loadEntry("audio/x-ms-wax", "wax")
        loadEntry("audio/x-pn-realaudio", "ra")
        loadEntry("audio/x-pn-realaudio", "rm")
        loadEntry("audio/x-pn-realaudio", "ram")
        loadEntry("audio/x-realaudio", "ra")
        loadEntry("audio/x-scpls", "pls")
        loadEntry("audio/x-sd2", "sd2")
        loadEntry("audio/x-wav", "wav")
        loadEntry("image/bmp", "bmp")
        loadEntry("image/gif", "gif")
        loadEntry("image/ico", "cur")
        loadEntry("image/ico", "ico")
        loadEntry("image/ief", "ief")
        loadEntry("image/jpeg", "jpeg")
        loadEntry("image/jpeg", "jpg")
        loadEntry("image/jpeg", "jpe")
        loadEntry("image/pcx", "pcx")
        loadEntry("image/png", "png")
        loadEntry("image/svg+xml", "svg")
        loadEntry("image/svg+xml", "svgz")
        loadEntry("image/tiff", "tiff")
        loadEntry("image/tiff", "tif")
        loadEntry("image/vnd.djvu", "djvu")
        loadEntry("image/vnd.djvu", "djv")
        loadEntry("image/vnd.wap.wbmp", "wbmp")
        loadEntry("image/x-cmu-raster", "ras")
        loadEntry("image/x-coreldraw", "cdr")
        loadEntry("image/x-coreldrawpattern", "pat")
        loadEntry("image/x-coreldrawtemplate", "cdt")
        loadEntry("image/x-corelphotopaint", "cpt")
        loadEntry("image/x-icon", "ico")
        loadEntry("image/x-jg", "art")
        loadEntry("image/x-jng", "jng")
        loadEntry("image/x-ms-bmp", "bmp")
        loadEntry("image/x-photoshop", "psd")
        loadEntry("image/x-portable-anymap", "pnm")
        loadEntry("image/x-portable-bitmap", "pbm")
        loadEntry("image/x-portable-graymap", "pgm")
        loadEntry("image/x-portable-pixmap", "ppm")
        loadEntry("image/x-rgb", "rgb")
        loadEntry("image/x-xbitmap", "xbm")
        loadEntry("image/x-xpixmap", "xpm")
        loadEntry("image/x-xwindowdump", "xwd")
        loadEntry("model/iges", "igs")
        loadEntry("model/iges", "iges")
        loadEntry("model/mesh", "msh")
        loadEntry("model/mesh", "mesh")
        loadEntry("model/mesh", "silo")
        loadEntry("text/calendar", "ics")
        loadEntry("text/calendar", "icz")
        loadEntry("text/comma-separated-values", "csv")
        loadEntry("text/css", "css")
        loadEntry("text/html", "htm")
        loadEntry("text/html", "html")
        loadEntry("text/h323", "323")
        loadEntry("text/iuls", "uls")
        loadEntry("text/mathml", "mml")
        loadEntry("text/plain", "txt")
        loadEntry("text/plain", "asc")
        loadEntry("text/plain", "text")
        loadEntry("text/plain", "diff")
        loadEntry("text/plain", "po")
        loadEntry("text/richtext", "rtx")
        loadEntry("text/rtf", "rtf")
        loadEntry("text/texmacs", "ts")
        loadEntry("text/text", "phps")
        loadEntry("text/tab-separated-values", "tsv")
        loadEntry("text/xml", "xml")
        loadEntry("text/x-bibtex", "bib")
        loadEntry("text/x-boo", "boo")
        loadEntry("text/x-c++hdr", "h++")
        loadEntry("text/x-c++hdr", "hpp")
        loadEntry("text/x-c++hdr", "hxx")
        loadEntry("text/x-c++hdr", "hh")
        loadEntry("text/x-c++src", "c++")
        loadEntry("text/x-c++src", "cpp")
        loadEntry("text/x-c++src", "cxx")
        loadEntry("text/x-chdr", "h")
        loadEntry("text/x-component", "htc")
        loadEntry("text/x-csh", "csh")
        loadEntry("text/x-csrc", "c")
        loadEntry("text/x-dsrc", "d")
        loadEntry("text/x-haskell", "hs")
        loadEntry("text/x-java", "java")
        loadEntry("text/x-literate-haskell", "lhs")
        loadEntry("text/x-moc", "moc")
        loadEntry("text/x-pascal", "p")
        loadEntry("text/x-pascal", "pas")
        loadEntry("text/x-pcs-gcd", "gcd")
        loadEntry("text/x-setext", "etx")
        loadEntry("text/x-tcl", "tcl")
        loadEntry("text/x-tex", "tex")
        loadEntry("text/x-tex", "ltx")
        loadEntry("text/x-tex", "sty")
        loadEntry("text/x-tex", "cls")
        loadEntry("text/x-vcalendar", "vcs")
        loadEntry("text/x-vcard", "vcf")
        loadEntry("video/3gpp", "3gpp")
        loadEntry("video/3gpp", "3gp")
        loadEntry("video/3gpp", "3g2")
        loadEntry("video/dl", "dl")
        loadEntry("video/dv", "dif")
        loadEntry("video/dv", "dv")
        loadEntry("video/fli", "fli")
        loadEntry("video/m4v", "m4v")
        loadEntry("video/mpeg", "mpeg")
        loadEntry("video/mpeg", "mpg")
        loadEntry("video/mpeg", "mpe")
        loadEntry("video/mp4", "mp4")
        loadEntry("video/mpeg", "VOB")
        loadEntry("video/quicktime", "qt")
        loadEntry("video/quicktime", "mov")
        loadEntry("video/vnd.mpegurl", "mxu")
        loadEntry("video/x-la-asf", "lsf")
        loadEntry("video/x-la-asf", "lsx")
        loadEntry("video/x-mng", "mng")
        loadEntry("video/x-ms-asf", "asf")
        loadEntry("video/x-ms-asf", "asx")
        loadEntry("video/x-ms-wm", "wm")
        loadEntry("video/x-ms-wmv", "wmv")
        loadEntry("video/x-ms-wmx", "wmx")
        loadEntry("video/x-ms-wvx", "wvx")
        loadEntry("video/x-msvideo", "avi")
        loadEntry("video/x-sgi-movie", "movie")
        loadEntry("x-conference/x-cooltalk", "ice")
        loadEntry("x-epoc/x-sisx-app", "sisx")
    }

}
