(ns campeterson.clj-browser.user-agent)

(defn opera? [ua]
  (some? (re-seq #"Opera|OPR" ua)))

(defn opera_mini? [ua]
  (some? (re-seq #"Opera Mini" ua)))

(defn android? [ua]
  (and
    (some? (re-seq #"Android" ua)) 
    (not (opera? ua))))

(defn blackberry? [ua]
  (some? (re-seq #"BlackBerry" ua)))

(defn blackberry4? [ua]
  (and
    (blackberry? ua)
    (some? 
      (re-seq #"BlackBerry\d+/4" ua))))

(defn blackberry5? [ua]
  (and
    (blackberry? ua)
    (some? 
      (re-seq #"BlackBerry\d+/5" ua))))

(defn blackberry6? [ua]
  (and
    (blackberry? ua)
    (some? 
      (re-seq #"Version/6" ua))))

(defn blackberry7? [ua]
  (and
    (blackberry? ua)
    (some? 
      (re-seq #"Version/7" ua))))

(defn blackberry10? [ua]
  (and
    (blackberry? ua)
    (some? 
      (re-seq #"BB10" ua))))

; TODO-
; Improve this
(defn bot? [ua]
  (some? (re-seq #"(nuhk|Googlebot|Yammybot|Openbot|Slurp|MSNBot|Ask Jeeves|Teoma|ia_archiver|Yandex)" ua)))

; TODO-
; https://github.com/fnando/browser/blob/master/lib/browser/methods/bots.rb
(defn search_engine? [ua]
  (bot? ua))

(defn chrome? [ua]
  (and
    (some? (re-seq #"Chrome|CriOS" ua)) 
    (not (opera? ua))))

(defn chrome_os? [ua]
  (some? (re-seq #"CrOS" ua)))

; TODO-
; https://github.com/fnando/browser/blob/master/lib/browser/methods/ie.rb
(defn compatibility_view? [ua]
  false)

(defn xbox? [ua]
  (some? (re-seq #"(?i)xbox" ua)))

(defn xbox_one? [ua]
  (some? (re-seq #"(?i)xbox one" ua)))

(defn playstation? [ua]
  (some? (re-seq #"(?i)playstation" ua)))

(defn playstation4? [ua]
  (some? (re-seq #"(?i)playstation 4" ua)))

(defn psp_vita? [ua]
  (some? (re-seq #"Playstation Vita" ua)))

(defn psp? [ua]
  (or
    (some? (re-seq #"PSP" ua)) 
    (psp_vita? ua)))

(defn nintendo? [ua]
  (some? (re-seq #"(?i)nintendo" ua)))

(defn console? [ua]
  (or
    (xbox? ua)
    (playstation? ua)
    (nintendo? ua)))

(defn core_media? [ua]
  (some? (re-seq #"CoreMedia" ua)))

(defn firefox? [ua]
  (some? (re-seq #"Firefox" ua)))

(defn msie? [ua]
  (and
    (some? (re-seq #"MSIE" ua)) 
    (not (opera? ua))))

(defn modern_ie? [ua]
  (some? (re-seq #"MODERN_IE" ua)))

(defn ie? [ua]
  (or (msie? ua) (modern_ie? ua)))

; TODO-
(defn ie6? [ua]
  false)

; TODO-
(defn ie7? [ua]
  false)

; TODO-
(defn ie8? [ua]
  false)

; TODO-
(defn ie9? [ua]
  false)

; TODO-
(defn ie10? [ua]
  false)

; TODO-
(defn ie11? [ua]
  false)

(defn ipad? [ua]
  (some? (re-seq #"iPad" ua)))

(defn iphone? [ua]
  (some? (re-seq #"iPhone" ua)))

(defn ipod? [ua]
  (some? (re-seq #"iPod" ua)))

(defn ios? [ua]
  (or
    (ipod? ua)
    (ipad? ua)  
    (iphone? ua)))

(defn ios4? [ua]
  (and
    (ios? ua)
    (some? (re-seq #"OS 4" ua))))

(defn ios5? [ua]
  (and
    (ios? ua)
    (some? (re-seq #"OS 5" ua))))

(defn ios6? [ua]
  (and
    (ios? ua)
    (some? (re-seq #"OS 6" ua))))

(defn ios7? [ua]
  (and
    (ios? ua)
    (some? (re-seq #"OS 7" ua))))

(defn ios8? [ua]
  (and
    (ios? ua)
    (some? (re-seq #"OS 8" ua))))

(defn silk? [ua]
  (some? (re-seq #"Silk" ua)))

(defn kindle? [ua]
  (or
    (some? (re-seq #"Kindle" ua)) 
    (silk? ua)))

(defn linux? [ua]
  (some? (re-seq #"Linux|X11" ua)))

(defn mac-os-x? [ua]
  (and
    (some? (re-seq #"Mac OS X" ua))
    (not (ios? ua))))

(defn mac-os? [ua]
  (and
    (some? (re-seq #"MacPPC|MacIntel|Mac_PowerPC|Macintosh" ua))
    (not (ios? ua))))

(defn mac? [ua]
  (or (mac-os-x? ua)
      (mac-os? ua)))

; TODO - Improve this method
; https://github.com/fnando/browser/blob/master/lib/browser/methods/mobile.rb
(defn detect_mobile? [ua]
  (or
    (psp? ua)
    (some? (re-seq #"Mobile|mini|Fennec|Android|iPad|iPod|iPhone" ua))))

(defn windows-8_1? [ua]
  (some? (re-seq #"Windows 8.1|Windows NT 6.3" ua)))

(defn windows-8? [ua]
  (or (windows-8_1? ua)
      (some? (re-seq #"Windows 8|Windows NT 6.2" ua))))

(defn windows_rt? [ua]
  (and
    (windows-8? ua)
    (not 
      (some? (re-seq #"ARM" ua)))))

(defn surface? [ua]
  (and
    (windows_rt? ua)
    (some? (re-seq #"Touch" ua))))

(defn playbook? [ua]
  (or
    (some? (re-seq #"PlayBook" ua)) 
    (some? (re-seq #"RIM Tablet" ua))))

(defn tablet? [ua]
  (or
    (ipad? ua)
    (and (android? ua) (not (detect_mobile? ua)))
    (surface? ua)
    (playbook? ua)))

(defn mobile? [ua]
  (and
    (detect_mobile? ua)
    (not (tablet? ua))))

; TODO-
; https://github.com/fnando/browser/search?utf8=%E2%9C%93&q=modern&type=Code
(defn modern? [ua]
  false)

(defn phantom_js? [ua]
  (some? (re-seq #"PhantomJS" ua)))

; TODO-
; https://github.com/fnando/browser/blob/master/lib/browser.rb
(defn quicktime? [ua]
  false)

(defn safari_webapp_mode? [ua]
  (and
    (or (ipad? ua) (iphone? ua))
    (some? (re-seq #"AppleWebKit" ua))))

(defn safari? [ua]
  (and
    (or
      (safari_webapp_mode? ua)
      (some? (re-seq #"Safari" ua)))
    (not (some? (re-seq #"Android|Chrome|CriOS|PhantomJS" ua)))))

(defn tv? [ua]
  (some? (re-seq #"(?i)tv" ua)))

; TODO-
; https://github.com/fnando/browser/search?utf8=%E2%9C%93&q=webkit
(defn webkit? [ua]
  false)

(defn windows? [ua]
  (some? (re-seq #"Windows" ua)))

(defn windows_mobile? [ua]
  (some? (re-seq #"Windows CE" ua)))

(defn windows_phone? [ua]
  (some? (re-seq #"Windows Phone" ua)))

(defn windows_touchscreen_desktop? [ua]
  (and
    (windows? ua)
    (some? (re-seq #"Touch" ua))))

(defn windows-xp? [ua]
  (and
    (windows? ua)
    (some? (re-seq #"Windows NT 5.1|Windows XP" ua))))

(defn windows-vista? [ua]
  (and
    (windows? ua)
    (some? (re-seq #"Windows NT 6.0" ua))))

(defn windows-7? [ua]
  (and
    (windows? ua)
    (some? (re-seq #"Windows 7|Windows NT 6.1" ua))))

(defn windows_x64? [ua]
  (and
    (windows? ua)
    (some? (re-seq #"(Win64|x64)" ua))  
    (some? (re-seq #"x64" ua))))

(defn windows-3_1? [ua]
  (some? (re-seq #"Win16" ua)))

(defn windows-95? [ua]
  (some? (re-seq #"Windows 95|Win95|Windows_95" ua)))

(defn windows-me? [ua]
  (some? (re-seq #"Win 9x 4.90|Windows ME" ua)))

(defn windows-98? [ua]
  (some? (re-seq #"Windows 98|Win98" ua)))

(defn windows-ce? [ua]
  (some? (re-seq #"Windows CE" ua)))

(defn windows-2000? [ua]
  (some? (re-seq #"Windows NT 5.0|Windows 2000" ua)))

(defn windows-server-2003? [ua]
  (some? (re-seq #"Windows NT 5.2" ua)))

(defn windows-nt-4? [ua]
  (some? (re-seq #"Windows NT 4.0|WinNT4.0|WinNT|Windows NT" ua)))

(defn open-bsd? [ua]
  (some? (re-seq #"OpenBSD" ua)))

(defn sun-os? [ua]
  (some? (re-seq #"SunOS" ua)))

(defn qnx? [ua]
  (some? (re-seq #"QNX" ua)))

(defn unix? [ua]
  (some? (re-seq #"UNIX" ua)))

(defn beos? [ua]
  (some? (re-seq #"BeOS" ua)))

(defn os2? [ua]
  (some? (re-seq #"OS/2" ua)))

(defn generic? [ua]
  (and (not (safari? ua))
       (not (chrome? ua))))

(defn platform [ua]
  (cond
    (linux? ua) :linux
    (mac? ua) :mac
    (windows? ua) :windows
    :else :other))

(defn browser [ua]
  (cond
    (ie? ua) "Internet Explorer"
    (chrome? ua) "Chrome"
    (android? ua) "Android"
    (blackberry? ua) "Blackberry"
    (core_media? ua) "Apple CoreMedia"
    (firefox? ua) "Firefox"
    (ipad? ua) "iPad"
    (iphone? ua) "iPhone"
    (ipod? ua) "iPod Touch"
    (nintendo? ua) "Nintendo"
    (opera? ua) "Opera"
    (phantom_js? ua) "PhantomJS"
    (psp? ua) "PlayStation Portable"
    (playstation? ua) "PlayStation"
    (quicktime? ua) "Quicktime"
    (safari? ua) "Safari"
    (xbox? ua) "Xbox"
    :else "Other"))

;(defn browser-version [ua]
  ;(cond
    ;(windows? ua) "";(:version (zipmap [:version] (rest (re-find #"Windows (?<version>[^;]+)" ua))))
    ;(chrome? ua) (:version (zipmap [:version] (rest (re-find #"(Chrome|CriOS)\/(?<version>[\d.]+)" ua))))
    ;(ie? ua) (:version (zipmap [:version] (rest (re-find #"(?:MSIE |Trident\/.*?; rv:)(?<version>[\d.]+)" ua))))
    ;(opera? ua) (first (filter #(not (nil? %)) (rest (re-find #"(?:Opera\/.*? Version\/([\d.]+)|Chrome\/.*?OPR\/([\d.]+))" ua))))
    ;:else (:version (zipmap [:version] (rest (re-find #"(?i)(?:Version|MSIE|Firefox|QuickTime|BlackBerry[^\/]+|CoreMedia v|PhantomJS|AdobeAIR)[\/ ]?(?<version>[a-z0-9.]+)" ua))))))

(defn os [ua]
  (cond
    (windows-3_1? ua) "Windows 3.11"
    (windows-95? ua) "Windows 95"
    (windows-me? ua) "Windows ME"
    (windows-98? ua) "Windows 98"
    (windows-ce? ua) "Windows CE"
    (windows-2000? ua) "Windows 2000"
    (windows-xp? ua) "Windows XP"
    (windows-server-2003? ua) "Windows Server 2003"
    (windows-vista? ua) "Windows Vista"
    (windows-7? ua) "Windows 7"
    (windows-8_1? ua) "Windows 8.1"
    (windows-8? ua) "Windows 8"
    (windows-nt-4? ua) "Windows NT 4.0"
    (android? ua) "Android"
    (sun-os? ua) "Sun OS"
    (linux? ua) "Linux"
    (ios? ua) "iOS"
    (mac-os-x? ua) "Mac OS X"
    (mac-os? ua) "Mac OS"
    (qnx? ua) "QNX"
    (unix? ua) "UNIX"
    (beos? ua) "BeOS"
    (os2? ua) "OS/2"
    (search_engine? ua) "Search bot"))

; http://rubular.com/r/2DmzXCTrkU
;(defn os-version [ua]
  ;(cond
    ;(windows? ua) (:version (zipmap [:version] (rest (re-find #"Windows (?<version>[^;]+)" ua))))
    ;(mac-os-x? ua) (:version (zipmap [:version] (rest (re-find #"Mac OS X (?<version>10[\.\_\d]+)" ua))))
    ;(android? ua) (:version (zipmap [:version] (rest (re-find #"Android (?<version>[\.\_\d]+)" ua))))
    ;(ios? ua) (let [v-map (zipmap [:major :minor :release] (rest (re-find #"OS (?<major>\d+)_(?<minor>\d+)_?(?<release>\d+)?" ua)))]
                ;(str (:major v-map) "." (:minor v-map) "." (or (:release v-map) "0")))
    ;:else "unknown"))
