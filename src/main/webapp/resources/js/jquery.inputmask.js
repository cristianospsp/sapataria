
/*
 Input Mask plugin for jquery
 http://github.com/RobinHerbots/jquery.inputmask
 Copyright (c) 2010 - 2014 Robin Herbots
 Licensed under the MIT license (http://www.opensource.org/licenses/mit-license.php)
 Version: 3.0.37
 Input Mask plugin for jquery
 http://github.com/RobinHerbots/jquery.inputmask
 Copyright (c) 2010 - 2014 Robin Herbots
 Licensed under the MIT license (http://www.opensource.org/licenses/mit-license.php)
 Version: 3.0.37
 */
(function (d) {
    if (void 0 === d.fn.inputmask) {
        var a = function (a) {
            var d = document.createElement("input");
            a = "on" + a;
            var e = a in d;
            e || (d.setAttribute(a, "return;"), e = "function" == typeof d[a]);
            return e
        }, c = function (a, n, e) {
            return(a = e.aliases[a]) ? (a.alias && c(a.alias, void 0, e), d.extend(!0, e, a), d.extend(!0, e, n), !0) : !1
        }, h = function (a) {
            function c(d) {
                function e(a, d, c, n) {
                    this.matches = [];
                    this.isGroup = a || !1;
                    this.isOptional = d || !1;
                    this.isQuantifier = c || !1;
                    this.isAlternator = n || !1;
                    this.quantifier = {min: 1, max: 1}
                }
                function n(d, e, c) {
                    var b =
                            a.definitions[e], g = 0 == d.matches.length;
                    c = void 0 != c ? c : d.matches.length;
                    if (b && !h) {
                        for (var f = b.prevalidator, p = f ? f.length : 0, q = 1; q < b.cardinality; q++) {
                            var s = p >= q ? f[q - 1] : [], l = s.validator, s = s.cardinality;
                            d.matches.splice(c++, 0, {fn: l ? "string" == typeof l ? RegExp(l) : new function () {
                                    this.test = l
                                } : /./, cardinality: s ? s : 1, optionality: d.isOptional, newBlockMarker: g, casing: b.casing, def: b.definitionSymbol || e})
                        }
                        d.matches.splice(c++, 0, {fn: b.validator ? "string" == typeof b.validator ? RegExp(b.validator) : new function () {
                                this.test =
                                        b.validator
                            } : /./, cardinality: b.cardinality, optionality: d.isOptional, newBlockMarker: g, casing: b.casing, def: b.definitionSymbol || e})
                    } else
                        d.matches.splice(c++, 0, {fn: null, cardinality: 0, optionality: d.isOptional, newBlockMarker: g, casing: null, def: e}), h = !1
                }
                for (var b = /(?:[?*+]|\{[0-9\+\*]+(?:,[0-9\+\*]*)?\})\??|[^.?*+^${[]()|\\]+|./g, h = !1, g = new e, f, s = [], u = []; f = b.exec(d); )
                    switch (f = f[0], f.charAt(0)) {
                        case a.optionalmarker.end:
                        case a.groupmarker.end:
                            var l = s.pop();
                            0 < s.length ? s[s.length - 1].matches.push(l) : g.matches.push(l);
                            break;
                        case a.optionalmarker.start:
                            s.push(new e(!1, !0));
                            break;
                        case a.groupmarker.start:
                            s.push(new e(!0));
                            break;
                        case a.quantifiermarker.start:
                            l = new e(!1, !1, !0);
                            f = f.replace(/[{}]/g, "");
                            var t = f.split(",");
                            f = isNaN(t[0]) ? t[0] : parseInt(t[0]);
                            t = 1 == t.length ? f : isNaN(t[1]) ? t[1] : parseInt(t[1]);
                            if ("*" == t || "+" == t)
                                f = "*" == t ? 0 : 1;
                            l.quantifier = {min: f, max: t};
                            if (0 < s.length) {
                                t = s[s.length - 1].matches;
                                f = t.pop();
                                if (!f.isGroup) {
                                    var v = new e(!0);
                                    v.matches.push(f);
                                    f = v;
                                }
                                t.push(f);
                                t.push(l);
                            } else
                                f = g.matches.pop(), f.isGroup || (v = new e(!0),
                                        v.matches.push(f), f = v), g.matches.push(f), g.matches.push(l);
                            break;
                        case a.escapeChar:
                            h = !0;
                            break;
                        case a.alternatormarker:
                            break;
                        default:
                            0 < s.length ? n(s[s.length - 1], f) : (0 < g.matches.length && (l = g.matches[g.matches.length - 1], l.isGroup && (l.isGroup = !1, n(l, a.groupmarker.start, 0), n(l, a.groupmarker.end))), n(g, f))
                    }
                0 < g.matches.length && (l = g.matches[g.matches.length - 1], l.isGroup && (l.isGroup = !1, n(l, a.groupmarker.start, 0), n(l, a.groupmarker.end)), u.push(g));
                return u
            }
            function e(e, b) {
                if (a.numericInput && !0 !== a.multi) {
                    e =
                            e.split("").reverse();
                    for (var h = 0; h < e.length; h++)
                        e[h] == a.optionalmarker.start ? e[h] = a.optionalmarker.end : e[h] == a.optionalmarker.end ? e[h] = a.optionalmarker.start : e[h] == a.groupmarker.start ? e[h] = a.groupmarker.end : e[h] == a.groupmarker.end && (e[h] = a.groupmarker.start);
                    e = e.join("");
                }
                if (void 0 != e && "" != e) {
                    if (0 < a.repeat || "*" == a.repeat || "+" == a.repeat)
                        e = a.groupmarker.start + e + a.groupmarker.end + a.quantifiermarker.start + ("*" == a.repeat ? 0 : "+" == a.repeat ? 1 : a.repeat) + "," + a.repeat + a.quantifiermarker.end;
                    void 0 == d.inputmask.masksCache[e] &&
                            (d.inputmask.masksCache[e] = {mask: e, maskToken: c(e), validPositions: {}, _buffer: void 0, buffer: void 0, tests: {}, metadata: b});
                    return d.extend(!0, {}, d.inputmask.masksCache[e])
                }
            }
            var b = [];
            d.isFunction(a.mask) && (a.mask = a.mask.call(this, a));
            d.isArray(a.mask) ? d.each(a.mask, function (a, d) {
                void 0 != d.mask ? b.push(e(d.mask.toString(), d)) : b.push(e(d.toString()))
            }) : (1 == a.mask.length && !1 == a.greedy && 0 != a.repeat && (a.placeholder = ""), b = void 0 != a.mask.mask ? e(a.mask.mask.toString(), a.mask) : e(a.mask.toString()));
            return b
        }, g =
                "function" === typeof ScriptEngineMajorVersion ? ScriptEngineMajorVersion() : 10 <= (new Function("/*@cc_on return @_jscript_version; @*/"))(), b = navigator.userAgent, f = null !== b.match(/iphone/i), l = null !== b.match(/android.*safari.*/i), w = null !== b.match(/android.*chrome.*/i), v = null !== b.match(/android.*firefox.*/i), F = /Kindle/i.test(b) || /Silk/i.test(b) || /KFTT/i.test(b) || /KFOT/i.test(b) || /KFJWA/i.test(b) || /KFJWI/i.test(b) || /KFSOWI/i.test(b) || /KFTHWA/i.test(b) || /KFTHWI/i.test(b) || /KFAPWA/i.test(b) || /KFAPWI/i.test(b),
                G = a("paste") ? "paste" : a("input") ? "input" : "propertychange", t = function (a, c, e) {
            function b(a, d, h) {
                d = d || 0;
                var g = [], f, m = 0, p;
                do {
                    if (!0 === a && c.validPositions[m]) {
                        var k = c.validPositions[m];
                        p = k.match;
                        f = k.locator.slice();
                        g.push(null == p.fn ? p.def : !0 === h ? k.input : e.placeholder.charAt(m % e.placeholder.length))
                    } else
                        f = d > m ? P(m, f, m - 1)[0] : q(m, f, m - 1), p = f.match, f = f.locator.slice(), g.push(null == p.fn ? p.def : e.placeholder.charAt(m % e.placeholder.length));
                    m++
                } while ((void 0 == H || m - 1 < H) && null != p.fn || null == p.fn && "" != p.def || d >= m);
                g.pop();
                return g
            }
            function h(a) {
                var d = c;
                d.buffer = void 0;
                d.tests = {};
                !0 !== a && (d._buffer = void 0, d.validPositions = {}, d.p = -1)
            }
            function t(a) {
                var b = c, h = -1, g = b.validPositions;
                if (d.isFunction(e.getLastValidPosition))
                    h = e.getLastValidPosition.call(r, b, a, e);
                else {
                    void 0 == a && (a = -1);
                    var b = h, f;
                    for (f in g) {
                        var m = parseInt(f);
                        if (-1 == a || null != g[m].match.fn)
                            m < a && (b = m), m >= a && (h = m)
                    }
                    h = 1 < a - b || h < a ? b : h
                }
                return h
            }
            function p(a, b, h) {
                if (e.insertMode && void 0 != c.validPositions[a] && void 0 == h) {
                    h = d.extend(!0, {}, c.validPositions);
                    var g = t(), f;
                    for (f = a; f <= g; f++)
                        delete c.validPositions[f];
                    c.validPositions[a] = b;
                    b = !0;
                    for (f = a; f <= g; f++) {
                        a = h[f];
                        if (void 0 != a) {
                            var m = null == a.match.fn ? f + 1 : A(f);
                            b = J(m, a.match.def) ? b && !1 !== O(m, a.input, !0, !0) : !1
                        }
                        if (!b)
                            break
                    }
                    if (!b)
                        return c.validPositions = d.extend(!0, {}, h), !1
                } else
                    c.validPositions[a] = b;
                return!0
            }
            function q(a, c, d) {
                a = P(a, c, d);
                var b;
                for (c = 0; c < a.length && (b = a[c], !e.greedy && (!b.match || !1 !== b.match.optionality && !1 !== b.match.newBlockMarker || !0 === b.match.optionalQuantifier)); c++)
                    ;
                return b
            }
            function C(a) {
                return c.validPositions[a] ?
                        c.validPositions[a].match : P(a)[0].match
            }
            function J(a, c) {
                for (var d = !1, e = P(a), b = 0; b < e.length; b++)
                    if (e[b].match && e[b].match.def == c) {
                        d = !0;
                        break
                    }
                return d
            }
            function P(a, b, h) {
                function f(c, b, h, g) {
                    function R(h, g, n) {
                        if (m == a && void 0 == h.matches)
                            return p.push({match: h, locator: g.reverse()}), !0;
                        if (void 0 != h.matches)
                            if (h.isGroup && !0 !== n) {
                                if (h = R(c.matches[D + 1], g))
                                    return!0
                            } else if (h.isOptional) {
                                var q = h;
                                if (h = f(h, b, g, n))
                                    h = p[p.length - 1].match, (h = 0 == d.inArray(h, q.matches)) && (k = !0), m = a
                            } else {
                                if (!h.isAlternator)
                                    if (h.isQuantifier &&
                                            !0 !== n)
                                        for (q = h, e.greedy = e.greedy && isFinite(q.quantifier.max), n = 0 < b.length && !0 !== n ? b.shift() : 0; n < (isNaN(q.quantifier.max) ? n + 1 : q.quantifier.max) && m <= a; n++) {
                                            var s = c.matches[d.inArray(q, c.matches) - 1];
                                            if (h = R(s, [n].concat(g), !0))
                                                if (h = p[p.length - 1].match, h.optionalQuantifier = n > q.quantifier.min - 1, h = 0 == d.inArray(h, s.matches))
                                                    if (n > q.quantifier.min - 1) {
                                                        k = !0;
                                                        m = a;
                                                        break
                                                    } else
                                                        return!0;
                                                else
                                                    return!0
                                        }
                                    else if (h = f(h, b, g, n))
                                        return!0
                            }
                        else
                            m++
                    }
                    for (var D = 0 < b.length ? b.shift() : 0; D < c.matches.length; D++)
                        if (!0 !== c.matches[D].isQuantifier) {
                            var n =
                                    R(c.matches[D], [D].concat(h), g);
                            if (n && m == a)
                                return n;
                            if (m > a)
                                break
                        }
                }
                var g = c.maskToken, m = b ? h : 0;
                h = b || [0];
                var p = [], k = !1;
                if (void 0 == b) {
                    b = a - 1;
                    for (var q; void 0 == (q = c.validPositions[b]) && - 1 < b; )
                        b--;
                    if (void 0 != q && -1 < b)
                        m = b, h = q.locator.slice();
                    else {
                        for (b = a - 1; void 0 == (q = c.tests[b]) && - 1 < b; )
                            b--;
                        void 0 != q && -1 < b && (m = b, h = q[0].locator.slice())
                    }
                }
                for (b = h.shift(); b < g.length && !(f(g[b], h, [b]) && m == a || m > a); b++)
                    ;
                (0 == p.length || k) && p.push({match: {fn: null, cardinality: 0, optionality: !0, casing: null, def: ""}, locator: []});
                return c.tests[a] =
                p
            }
            function z() {
                void 0 == c._buffer && (c._buffer = b(!1, 1));
                return c._buffer
            }
            function u() {
                void 0 == c.buffer && (c.buffer = b(!0, t(), !0));
                return c.buffer
            }
            function Q(a, c) {
                for (var d = u().slice(), b = a; b < c; b++)
                    d[b] != e.skipOptionalPartCharacter && O(b, d[b], !0, !0)
            }
            function ia(a, c) {
                switch (c.casing) {
                    case "upper":
                        a = a.toUpperCase();
                        break;
                    case "lower":
                        a = a.toLowerCase()
                }
                return a
            }
            function O(a, b, g, f) {
                function q(a, b, ba, g) {
                    var f = !1;
                    d.each(P(a), function (R, D) {
                        for (var m = D.match, q = b ? 1 : 0, k = "", ja = u(), s = m.cardinality; s > q; s--)
                            k += void 0 ==
                                    c.validPositions[a - (s - 1)] ? V(a - (s - 1)) : c.validPositions[a - (s - 1)].input;
                        b && (k += b);
                        f = null != m.fn ? m.fn.test(k, ja, a, ba, e) : b != m.def && b != e.skipOptionalPartCharacter || "" == m.def ? !1 : {c: m.def, pos: a};
                        if (!1 !== f) {
                            q = void 0 != f.c ? f.c : b;
                            q = q == e.skipOptionalPartCharacter && null === m.fn ? m.def : q;
                            k = a;
                            if (f.refreshFromBuffer) {
                                k = f.refreshFromBuffer;
                                ba = !0;
                                !0 === k ? (c.validPositions = {}, c.tests = {}, Q(0, u().length)) : Q(k.start, k.end);
                                if (void 0 == f.pos && void 0 == f.c)
                                    return f.pos = t(), !1;
                                k = void 0 != f.pos ? f.pos : a;
                                if (k != a)
                                    return f = O(k, q, !0),
                                            !1
                            } else if (!0 !== f && f.pos != a && (k = f.pos, Q(a, k), k != a))
                                return f = O(k, q, !0), !1;
                            0 < R && h(!0);
                            p(k, d.extend({}, D, {input: ia(q, m)}), g) || (f = !1);
                            return!1
                        }
                    });
                    return f
                }
                g = !0 === g;
                if (a >= M())
                    return!1;
                var m = q(a, b, g, f);
                if (!g && !1 === m) {
                    var k = c.validPositions[a];
                    if (k && null == k.match.fn && (k.match.def == b || b == e.skipOptionalPartCharacter))
                        m = {caret: A(a)};
                    else if ((e.insertMode || void 0 == c.validPositions[A(a)]) && !L(a))
                        for (var k = a + 1, s = A(a); k <= s; k++)
                            if (m = q(k, b, g, f), !1 !== m) {
                                a = k;
                                break
                            }
                }
                !0 === m && (m = {pos: a});
                return m
            }
            function L(a) {
                a = C(a);
                return null != a.fn ? a.fn : !1
            }
            function M() {
                var a;
                H = r.prop("maxLength");
                -1 == H && (H = void 0);
                if (!1 == e.greedy) {
                    var b;
                    b = t();
                    a = c.validPositions[b];
                    var d = void 0 != a ? a.locator.slice() : void 0;
                    for (b += 1; void 0 == a || null != a.match.fn || null == a.match.fn && "" != a.match.def; b++)
                        a = q(b, d, b - 1), d = a.locator.slice();
                    a = b
                } else
                    a = u().length;
                return void 0 == H || a < H ? a : H
            }
            function A(a) {
                var c = M();
                if (a >= c)
                    return c;
                for (; ++a < c && !L(a) && (!0 !== e.nojumps || e.nojumpsThreshold > a); )
                    ;
                return a
            }
            function U(a) {
                if (0 >= a)
                    return 0;
                for (; 0 < --a && !L(a); )
                    ;
                return a
            }
            function B(a, c, b) {
                a._valueSet(c.join(""));
                void 0 != b && x(a, b)
            }
            function V(a, c) {
                c = c || C(a);
                return null == c.fn ? c.def : e.placeholder.charAt(a % e.placeholder.length)
            }
            function N(a, b, e, f, g) {
                f = void 0 != f ? f.slice() : ha(a._valueGet()).split("");
                h();
                b && a._valueSet("");
                d.each(f, function (b, h) {
                    if (!0 === g) {
                        var f = c.p, f = -1 == f ? f : U(f), p = -1 == f ? b : A(f);
                        -1 == d.inArray(h, z().slice(f + 1, p)) && X.call(a, void 0, !0, h.charCodeAt(0), !1, e, b)
                    } else
                        X.call(a, void 0, !0, h.charCodeAt(0), !1, e, b), e = e || 0 < b && b > c.p
                });
                b && B(a, u(), d(a).is(":focus") ? A(t(0)) :
                        void 0)
            }
            function $(a) {
                return d.inputmask.escapeRegex.call(this, a)
            }
            function ha(a) {
                return a.replace(RegExp("(" + $(z().join("")) + ")*$"), "")
            }
            function aa(a, b) {
                if (!a.data("_inputmask") || !0 !== b && a.hasClass("hasDatepicker"))
                    return a[0]._valueGet();
                var h = [], f = c.validPositions, g;
                for (g in f)
                    f[g].match && null != f[g].match.fn && h.push(f[g].input);
                h = (y ? h.reverse() : h).join("");
                f = (y ? u().reverse() : u()).join("");
                return d.isFunction(e.onUnMask) ? e.onUnMask.call(a, f, h, e) : h
            }
            function K(a) {
                !y || "number" != typeof a || e.greedy &&
                        "" == e.placeholder || (a = u().length - a);
                return a
            }
            function x(a, c, b) {
                a = a.jquery && 0 < a.length ? a[0] : a;
                if ("number" == typeof c) {
                    c = K(c);
                    b = K(b);
                    b = "number" == typeof b ? b : c;
                    var h = d(a).data("_inputmask") || {};
                    h.caret = {begin: c, end: b};
                    d(a).data("_inputmask", h);
                    d(a).is(":visible") && (a.scrollLeft = a.scrollWidth, !1 == e.insertMode && c == b && b++, a.setSelectionRange ? (a.selectionStart = c, a.selectionEnd = b) : a.createTextRange && (a = a.createTextRange(), a.collapse(!0), a.moveEnd("character", b), a.moveStart("character", c), a.select()))
                } else
                    return h =
                            d(a).data("_inputmask"), !d(a).is(":visible") && h && void 0 != h.caret ? (c = h.caret.begin, b = h.caret.end) : a.setSelectionRange ? (c = a.selectionStart, b = a.selectionEnd) : document.selection && document.selection.createRange && (a = document.selection.createRange(), c = 0 - a.duplicate().moveStart("character", -1E5), b = c + a.text.length), c = K(c), b = K(b), {begin: c, end: b}
            }
            function ca(a) {
                var b = u(), h = b.length, e, f = t(), g = {}, p = void 0 != c.validPositions[f] ? c.validPositions[f].locator.slice() : void 0, k;
                for (e = f + 1; e < b.length; e++)
                    k = q(e, p, e - 1), p =
                            k.locator.slice(), g[e] = d.extend(!0, {}, k);
                for (e = h - 1; e > f; e--)
                    if (k = g[e].match, (k.optionality || k.optionalQuantifier) && b[e] == V(e, k))
                        h--;
                    else
                        break;
                return a ? {l: h, def: g[h] ? g[h].match : void 0} : h
            }
            function Y(a) {
                var c = u().slice(), b = ca();
                c.length = b;
                B(a, c)
            }
            function S(a) {
                if (d.isFunction(e.isComplete))
                    return e.isComplete.call(r, a, e);
                if ("*" != e.repeat) {
                    var c = !1, b = ca(!0), h = U(b.l);
                    if (t() == h && (void 0 == b.def || b.def.newBlockMarker || b.def.optionalQuantifier))
                        for (c = !0, b = 0; b <= h; b++) {
                            var f = L(b);
                            if (f && (void 0 == a[b] || a[b] == V(b)) ||
                                    !f && a[b] != V(b)) {
                                c = !1;
                                break
                            }
                        }
                    return c
                }
            }
            function ka(a) {
                a = d._data(a).events;
                d.each(a, function (a, c) {
                    d.each(c, function (a, c) {
                        if ("inputmask" == c.namespace && "setvalue" != c.type) {
                            var b = c.handler;
                            c.handler = function (a) {
                                if (this.readOnly || this.disabled)
                                    a.preventDefault;
                                else
                                    return b.apply(this, arguments)
                            }
                        }
                    })
                })
            }
            function la(a) {
                function c(a) {
                    if (void 0 == d.valHooks[a] || !0 != d.valHooks[a].inputmaskpatch) {
                        var b = d.valHooks[a] && d.valHooks[a].get ? d.valHooks[a].get : function (a) {
                            return a.value
                        }, e = d.valHooks[a] && d.valHooks[a].set ?
                                d.valHooks[a].set : function (a, c) {
                            a.value = c;
                            return a
                        };
                        d.valHooks[a] = {get: function (a) {
                                var c = d(a);
                                if (c.data("_inputmask")) {
                                    if (c.data("_inputmask").opts.autoUnmask)
                                        return c.inputmask("unmaskedvalue");
                                    a = b(a);
                                    c = (c = c.data("_inputmask").maskset._buffer) ? c.join("") : "";
                                    return a != c ? a : ""
                                }
                                return b(a)
                            }, set: function (a, c) {
                                var b = d(a), h = e(a, c);
                                b.data("_inputmask") && b.triggerHandler("setvalue.inputmask");
                                return h
                            }, inputmaskpatch: !0}
                    }
                }
                var b;
                Object.getOwnPropertyDescriptor && (b = Object.getOwnPropertyDescriptor(a, "value"));
                if (b && b.get) {
                    if (!a._valueGet) {
                        var e = b.get, h = b.set;
                        a._valueGet = function () {
                            return y ? e.call(this).split("").reverse().join("") : e.call(this)
                        };
                        a._valueSet = function (a) {
                            h.call(this, y ? a.split("").reverse().join("") : a)
                        };
                        Object.defineProperty(a, "value", {get: function () {
                                var a = d(this), c = d(this).data("_inputmask");
                                return c ? c.opts.autoUnmask ? a.inputmask("unmaskedvalue") : e.call(this) != z().join("") ? e.call(this) : "" : e.call(this)
                            }, set: function (a) {
                                h.call(this, a);
                                d(this).triggerHandler("setvalue.inputmask")
                            }})
                    }
                } else
                    document.__lookupGetter__ &&
                            a.__lookupGetter__("value") ? a._valueGet || (e = a.__lookupGetter__("value"), h = a.__lookupSetter__("value"), a._valueGet = function () {
                        return y ? e.call(this).split("").reverse().join("") : e.call(this)
                    }, a._valueSet = function (a) {
                        h.call(this, y ? a.split("").reverse().join("") : a)
                    }, a.__defineGetter__("value", function () {
                        var a = d(this), c = d(this).data("_inputmask");
                        return c ? c.opts.autoUnmask ? a.inputmask("unmaskedvalue") : e.call(this) != z().join("") ? e.call(this) : "" : e.call(this)
                    }), a.__defineSetter__("value", function (a) {
                        h.call(this,
                                a);
                        d(this).triggerHandler("setvalue.inputmask")
                    })) : (a._valueGet || (a._valueGet = function () {
                        return y ? this.value.split("").reverse().join("") : this.value
                    }, a._valueSet = function (a) {
                        this.value = y ? a.split("").reverse().join("") : a
                    }), c(a.type))
            }
            function da(a, b, d) {
                if (e.numericInput || y) {
                    switch (b) {
                        case e.keyCode.BACKSPACE:
                            b = e.keyCode.DELETE;
                            break;
                        case e.keyCode.DELETE:
                            b = e.keyCode.BACKSPACE
                    }
                    y && (a = d.end, d.end = d.begin, d.begin = a)
                }
                d.begin == d.end ? b == e.keyCode.BACKSPACE ? d.begin = U(d.begin) : b == e.keyCode.DELETE && d.end++ :
                        1 != d.end - d.begin || e.insertMode || b == e.keyCode.BACKSPACE && d.begin--;
                a = d.begin;
                var f = d.end;
                for (b = a; a < f; a++)
                    delete c.validPositions[a];
                for (a = f; a <= t(); ) {
                    var f = c.validPositions[a], g = c.validPositions[b];
                    void 0 != f && void 0 == g ? (J(b, f.match.def) && !1 !== O(b, f.input, !0) && (delete c.validPositions[a], a++), b++) : a++
                }
                for (b = t(); 0 < b && (void 0 == c.validPositions[b] || null == c.validPositions[b].match.fn); )
                    delete c.validPositions[b], b--;
                h(!0);
                b = A(-1);
                t() < b ? c.p = b : c.p = d.begin
            }
            function ea(a) {
                Z = !1;
                var b = this, g = d(b), k = a.keyCode,
                        p = x(b);
                k == e.keyCode.BACKSPACE || k == e.keyCode.DELETE || f && 127 == k || a.ctrlKey && 88 == k ? (a.preventDefault(), 88 == k && (E = u().join("")), da(b, k, p), B(b, u(), c.p), b._valueGet() == z().join("") && g.trigger("cleared"), e.showTooltip && g.prop("title", c.mask)) : k == e.keyCode.END || k == e.keyCode.PAGE_DOWN ? setTimeout(function () {
                    var c = A(t());
                    e.insertMode || c != M() || a.shiftKey || c--;
                    x(b, a.shiftKey ? p.begin : c, c)
                }, 0) : k == e.keyCode.HOME && !a.shiftKey || k == e.keyCode.PAGE_UP ? x(b, 0, a.shiftKey ? p.begin : 0) : k == e.keyCode.ESCAPE || 90 == k && a.ctrlKey ?
                        (N(b, !0, !1, E.split("")), g.click()) : k != e.keyCode.INSERT || a.shiftKey || a.ctrlKey ? !1 != e.insertMode || a.shiftKey || (k == e.keyCode.RIGHT ? setTimeout(function () {
                    var a = x(b);
                    x(b, a.begin)
                }, 0) : k == e.keyCode.LEFT && setTimeout(function () {
                    var a = x(b);
                    x(b, a.begin - 1)
                }, 0)) : (e.insertMode = !e.insertMode, x(b, e.insertMode || p.begin != M() ? p.begin : p.begin - 1));
                var g = x(b), q = e.onKeyDown.call(this, a, u(), e);
                q && !0 === q.refreshFromBuffer && (c.validPositions = {}, c.tests = {}, Q(0, u().length), h(!0), B(b, u()), x(b, g.begin, g.end));
                fa = -1 != d.inArray(k,
                        e.ignorables)
            }
            function X(a, b, f, g, k, m) {
                if (void 0 == f && Z)
                    return!1;
                Z = !0;
                var s = d(this);
                a = a || window.event;
                f = b ? f : a.which || a.charCode || a.keyCode;
                if (!(!0 === b || a.ctrlKey && a.altKey) && (a.ctrlKey || a.metaKey || fa))
                    return!0;
                if (f) {
                    !0 !== b && 46 == f && !1 == a.shiftKey && "," == e.radixPoint && (f = 44);
                    var l;
                    f = String.fromCharCode(f);
                    b ? (m = k ? m : t() + 1, m = {begin: m, end: m}) : m = x(this);
                    var J = y ? 1 < m.begin - m.end || 1 == m.begin - m.end && e.insertMode : 1 < m.end - m.begin || 1 == m.end - m.begin && e.insertMode;
                    J && (c.undoPositions = d.extend(!0, {}, c.validPositions),
                            da(this, e.keyCode.DELETE, m), e.insertMode || (e.insertMode = !e.insertMode, p(m.begin, k), e.insertMode = !e.insertMode), J = !e.multi);
                    c.writeOutBuffer = !0;
                    var C = m.begin, r = O(C, f, k);
                    !1 !== r && (!0 !== r && (C = void 0 != r.pos ? r.pos : C, f = void 0 != r.c ? r.c : f), h(!0), void 0 != r.caret ? l = r.caret : (k = c.validPositions, l = void 0 != k[C + 1] && q(m + 1, k[C].locator.slice(), C).match.def != k[C + 1].match.def ? C + 1 : A(C)), c.p = l);
                    if (!1 !== g) {
                        var v = this;
                        setTimeout(function () {
                            e.onKeyValidation.call(v, r, e)
                        }, 0);
                        if (c.writeOutBuffer && !1 !== r) {
                            var z = u();
                            B(this, z,
                                    b ? void 0 : e.numericInput ? U(l) : l);
                            !0 !== b && setTimeout(function () {
                                !0 === S(z) && s.trigger("complete");
                                T = !0;
                                s.trigger("input")
                            }, 0)
                        } else
                            J && (c.buffer = void 0, c.validPositions = c.undoPositions)
                    } else
                        J && (c.buffer = void 0, c.validPositions = c.undoPositions);
                    e.showTooltip && s.prop("title", c.mask);
                    a && !0 != b && (a.preventDefault ? a.preventDefault() : a.returnValue = !1)
                }
            }
            function ma(a) {
                var b = d(this), f = a.keyCode, g = u();
                (a = e.onKeyUp.call(this, a, g, e)) && !0 === a.refreshFromBuffer && (c.validPositions = {}, c.tests = {}, Q(0, u().length), h(!0),
                        B(this, u()));
                f == e.keyCode.TAB && e.showMaskOnFocus && (b.hasClass("focus.inputmask") && 0 == this._valueGet().length ? (h(), g = u(), B(this, g), x(this, 0), E = u().join("")) : (B(this, g), x(this, K(0), K(M()))))
            }
            function ga(a) {
                if (!0 === T && "input" == a.type)
                    return T = !1, !0;
                var b = this, c = d(b);
                if ("propertychange" == a.type && b._valueGet().length <= M())
                    return!0;
                setTimeout(function () {
                    var a = d.isFunction(e.onBeforePaste) ? e.onBeforePaste.call(b, b._valueGet(), e) : b._valueGet();
                    N(b, !0, !1, a.split(""), !0);
                    !0 === S(u()) && c.trigger("complete");
                    c.click()
                }, 0)
            }
            function na(a) {
                if (!0 === T && "input" == a.type)
                    return T = !1, !0;
                var b = x(this), c = this._valueGet(), c = c.replace(RegExp("(" + $(z().join("")) + ")*"), "");
                b.begin > c.length && (x(this, c.length), b = x(this));
                1 != u().length - c.length || c.charAt(b.begin) == u()[b.begin] || c.charAt(b.begin + 1) == u()[b.begin] || L(b.begin) || (a.keyCode = e.keyCode.BACKSPACE, ea.call(this, a));
                a.preventDefault()
            }
            function oa(a) {
                r = d(a);
                if (r.is(":input") && "number" != r.attr("type")) {
                    r.data("_inputmask", {maskset: c, opts: e, isRTL: !1});
                    e.showTooltip &&
                            r.prop("title", c.mask);
                    la(a);
                    ("rtl" == a.dir || e.rightAlign) && r.css("text-align", "right");
                    if ("rtl" == a.dir || e.numericInput) {
                        a.dir = "ltr";
                        r.removeAttr("dir");
                        var b = r.data("_inputmask");
                        b.isRTL = !0;
                        r.data("_inputmask", b);
                        y = !0
                    }
                    r.unbind(".inputmask");
                    r.removeClass("focus.inputmask");
                    r.closest("form").bind("submit", function () {
                        E != u().join("") && r.change()
                    }).bind("reset", function () {
                        setTimeout(function () {
                            r.trigger("setvalue")
                        }, 0)
                    });
                    r.bind("mouseenter.inputmask", function () {
                        !d(this).hasClass("focus.inputmask") && e.showMaskOnHover &&
                                this._valueGet() != u().join("") && B(this, u())
                    }).bind("blur.inputmask", function () {
                        var a = d(this);
                        if (a.data("_inputmask")) {
                            var b = this._valueGet(), c = u();
                            a.removeClass("focus.inputmask");
                            E != u().join("") && a.change();
                            e.clearMaskOnLostFocus && "" != b && (b == z().join("") ? this._valueSet("") : Y(this));
                            !1 === S(c) && (a.trigger("incomplete"), e.clearIncomplete && (h(), e.clearMaskOnLostFocus ? this._valueSet("") : (c = z().slice(), B(this, c))))
                        }
                    }).bind("focus.inputmask", function () {
                        var a = d(this), b = this._valueGet();
                        e.showMaskOnFocus &&
                                !a.hasClass("focus.inputmask") && (!e.showMaskOnHover || e.showMaskOnHover && "" == b) && this._valueGet() != u().join("") && B(this, u(), A(t()));
                        a.addClass("focus.inputmask");
                        E = u().join("")
                    }).bind("mouseleave.inputmask", function () {
                        var a = d(this);
                        e.clearMaskOnLostFocus && (a.hasClass("focus.inputmask") || this._valueGet() == a.attr("placeholder") || (this._valueGet() == z().join("") || "" == this._valueGet() ? this._valueSet("") : Y(this)))
                    }).bind("click.inputmask", function () {
                        var a = this;
                        setTimeout(function () {
                            var b = x(a);
                            u();
                            if (b.begin ==
                                    b.end) {
                                var b = y ? K(b.begin) : b.begin, c = t(b), c = A(c);
                                b < c ? L(b) ? x(a, b) : x(a, A(b)) : x(a, c)
                            }
                        }, 0)
                    }).bind("dblclick.inputmask", function () {
                        var a = this;
                        setTimeout(function () {
                            x(a, 0, A(t()))
                        }, 0)
                    }).bind(G + ".inputmask dragdrop.inputmask drop.inputmask", ga).bind("setvalue.inputmask", function () {
                        N(this, !0);
                        E = u().join("");
                        this._valueGet() == z().join("") && this._valueSet("")
                    }).bind("complete.inputmask", e.oncomplete).bind("incomplete.inputmask", e.onincomplete).bind("cleared.inputmask", e.oncleared);
                    r.bind("keydown.inputmask",
                            ea).bind("keypress.inputmask", X).bind("keyup.inputmask", ma);
                    if (l || v || w || F)
                        "input" == G && r.unbind(G + ".inputmask"), r.bind("input.inputmask", na);
                    g && r.bind("input.inputmask", ga);
                    b = d.isFunction(e.onBeforeMask) ? e.onBeforeMask.call(a, a._valueGet(), e) : a._valueGet();
                    N(a, !0, !1, b.split(""), !0);
                    E = u().join("");
                    var f;
                    try {
                        f = document.activeElement
                    } catch (k) {
                    }
                    f === a ? (r.addClass("focus.inputmask"), x(a, A(t()))) : e.clearMaskOnLostFocus ? u().join("") == z().join("") ? a._valueSet("") : Y(a) : B(a, u());
                    ka(a)
                }
            }
            var y = !1, E, r, Z = !1, T = !1,
                    fa = !1, H;
            if (void 0 != a)
                switch (a.action) {
                    case "isComplete":
                        return r = d(a.el), c = r.data("_inputmask").maskset, e = r.data("_inputmask").opts, S(a.buffer);
                    case "unmaskedvalue":
                        return r = a.$input, c = r.data("_inputmask").maskset, e = r.data("_inputmask").opts, y = a.$input.data("_inputmask").isRTL, aa(a.$input, a.skipDatepickerCheck);
                    case "mask":
                        E = u().join("");
                        oa(a.el);
                        break;
                    case "format":
                        r = d({});
                        r.data("_inputmask", {maskset: c, opts: e, isRTL: e.numericInput});
                        e.numericInput && (y = !0);
                        var I = a.value.split("");
                        N(r, !1, !1, y ? I.reverse() :
                                I, !0);
                        return y ? u().reverse().join("") : u().join("");
                    case "isValid":
                        return r = d({}), r.data("_inputmask", {maskset: c, opts: e, isRTL: e.numericInput}), e.numericInput && (y = !0), I = a.value.split(""), N(r, !1, !0, y ? I.reverse() : I), S(u());
                    case "getemptymask":
                        return r = d(a.el), c = r.data("_inputmask").maskset, e = r.data("_inputmask").opts, z();
                    case "remove":
                        a = a.el;
                        r = d(a);
                        c = r.data("_inputmask").maskset;
                        e = r.data("_inputmask").opts;
                        a._valueSet(aa(r));
                        r.unbind(".inputmask");
                        r.removeClass("focus.inputmask");
                        r.removeData("_inputmask");
                        Object.getOwnPropertyDescriptor && (I = Object.getOwnPropertyDescriptor(a, "value"));
                        I && I.get ? a._valueGet && Object.defineProperty(a, "value", {get: a._valueGet, set: a._valueSet}) : document.__lookupGetter__ && a.__lookupGetter__("value") && a._valueGet && (a.__defineGetter__("value", a._valueGet), a.__defineSetter__("value", a._valueSet));
                        try {
                            delete a._valueGet, delete a._valueSet
                        } catch (qa) {
                            a._valueGet = void 0, a._valueSet = void 0
                        }
                    }
        };
        d.inputmask = {defaults: {placeholder: "_", optionalmarker: {start: "[", end: "]"}, quantifiermarker: {start: "{",
                    end: "}"}, groupmarker: {start: "(", end: ")"}, alternatormarker: "|", escapeChar: "\\", mask: null, oncomplete: d.noop, onincomplete: d.noop, oncleared: d.noop, repeat: 0, greedy: !0, autoUnmask: !1, clearMaskOnLostFocus: !0, insertMode: !0, clearIncomplete: !1, aliases: {}, alias: null, onKeyUp: d.noop, onKeyDown: d.noop, onBeforeMask: void 0, onBeforePaste: void 0, onUnMask: void 0, showMaskOnFocus: !0, showMaskOnHover: !0, onKeyValidation: d.noop, skipOptionalPartCharacter: " ", showTooltip: !1, numericInput: !1, getLastValidPosition: void 0, rightAlign: !1,
                radixPoint: "", definitions: {9: {validator: "[0-9]", cardinality: 1, definitionSymbol: "*"}, a: {validator: "[A-Za-z\u0410-\u044f\u0401\u0451]", cardinality: 1, definitionSymbol: "*"}, "*": {validator: "[A-Za-z\u0410-\u044f\u0401\u04510-9]", cardinality: 1}}, keyCode: {ALT: 18, BACKSPACE: 8, CAPS_LOCK: 20, COMMA: 188, COMMAND: 91, COMMAND_LEFT: 91, COMMAND_RIGHT: 93, CONTROL: 17, DELETE: 46, DOWN: 40, END: 35, ENTER: 13, ESCAPE: 27, HOME: 36, INSERT: 45, LEFT: 37, MENU: 93, NUMPAD_ADD: 107, NUMPAD_DECIMAL: 110, NUMPAD_DIVIDE: 111, NUMPAD_ENTER: 108, NUMPAD_MULTIPLY: 106,
                    NUMPAD_SUBTRACT: 109, PAGE_DOWN: 34, PAGE_UP: 33, PERIOD: 190, RIGHT: 39, SHIFT: 16, SPACE: 32, TAB: 9, UP: 38, WINDOWS: 91}, ignorables: [8, 9, 13, 19, 27, 33, 34, 35, 36, 37, 38, 39, 40, 45, 46, 93, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123], isComplete: void 0}, masksCache: {}, escapeRegex: function (a) {
                return a.replace(RegExp("(\\/|\\.|\\*|\\+|\\?|\\||\\(|\\)|\\[|\\]|\\{|\\}|\\\\)", "gim"), "\\$1")
            }, format: function (a, b) {
                var e = d.extend(!0, {}, d.inputmask.defaults, b);
                c(e.alias, b, e);
                return t({action: "format", value: a}, h(e), e)
            }, isValid: function (a,
                    b) {
                var e = d.extend(!0, {}, d.inputmask.defaults, b);
                c(e.alias, b, e);
                return t({action: "isValid", value: a}, h(e), e)
            }};
        d.fn.inputmask = function (a, b, e, f, g) {
            function l(a, b) {
                var c = d(a), e;
                for (e in b) {
                    var h = c.data("inputmask-" + e.toLowerCase());
                    void 0 != h && (b[e] = h)
                }
                return b
            }
            e = e || t;
            f = f || "_inputmask";
            var p = d.extend(!0, {}, d.inputmask.defaults, b), q;
            if ("string" === typeof a)
                switch (a) {
                    case "mask":
                        return c(p.alias, b, p), q = h(p), 0 == q.length ? this : this.each(function () {
                            e({action: "mask", el: this}, d.extend(!0, {}, d.isArray(q) && e === t ?
                                    q[0] : q), l(this, p))
                        });
                    case "unmaskedvalue":
                        return a = d(this), a.data(f) ? e({action: "unmaskedvalue", $input: a}) : a.val();
                    case "remove":
                        return this.each(function () {
                            d(this).data(f) && e({action: "remove", el: this})
                        });
                    case "getemptymask":
                        return this.data(f) ? e({action: "getemptymask", el: this}) : "";
                    case "hasMaskedValue":
                        return this.data(f) ? !this.data(f).opts.autoUnmask : !1;
                    case "isComplete":
                        return this.data(f) ? e({action: "isComplete", buffer: this[0]._valueGet().split(""), el: this}) : !0;
                    case "getmetadata":
                        if (this.data(f))
                            return q =
                                    this.data(f).maskset, q.metadata;
                        break;
                    case "_detectScope":
                        return c(p.alias, b, p), void 0 == g || c(g, b, p) || -1 != d.inArray(g, "mask unmaskedvalue remove getemptymask hasMaskedValue isComplete getmetadata _detectScope".split(" ")) || (p.mask = g), d.isFunction(p.mask) && (p.mask = p.mask.call(this, p)), d.isArray(p.mask);
                    default:
                        return c(a, b, p) || (p.mask = a), q = h(p), void 0 == q ? this : this.each(function () {
                            e({action: "mask", el: this}, d.extend(!0, {}, d.isArray(q) && e === t ? q[0] : q), l(this, p))
                        })
                }
            else {
                if ("object" == typeof a)
                    return p = d.extend(!0,
                            {}, d.inputmask.defaults, a), c(p.alias, a, p), q = h(p), void 0 == q ? this : this.each(function () {
                        e({action: "mask", el: this}, d.extend(!0, {}, d.isArray(q) && e === t ? q[0] : q), l(this, p))
                    });
                if (void 0 == a)
                    return this.each(function () {
                        var a = d(this).attr("data-inputmask");
                        if (a && "" != a)
                            try {
                                var a = a.replace(RegExp("'", "g"), '"'), h = d.parseJSON("{" + a + "}");
                                d.extend(!0, h, b);
                                p = d.extend(!0, {}, d.inputmask.defaults, h);
                                c(p.alias, h, p);
                                p.alias = void 0;
                                d(this).inputmask("mask", p, e)
                            } catch (f) {
                            }
                    })
            }
        }
    }
})(jQuery);
(function (d) {
    if (void 0 != d.fn.inputmask) {
        var a = function (a, h, g) {
            function b(a) {
                var b = document.createElement("input");
                a = "on" + a;
                var c = a in b;
                c || (b.setAttribute(a, "return;"), c = "function" == typeof b[a]);
                return c
            }
            function f(a) {
                if (void 0 == d.valHooks[a] || !0 != d.valHooks[a].inputmaskmultipatch) {
                    var b = d.valHooks[a] && d.valHooks[a].get ? d.valHooks[a].get : function (a) {
                        return a.value
                    }, c = d.valHooks[a] && d.valHooks[a].set ? d.valHooks[a].set : function (a, b) {
                        a.value = b;
                        return a
                    };
                    d.valHooks[a] = {get: function (a) {
                            var c = d(a);
                            return c.data("_inputmask-multi") ?
                                    (a = c.data("_inputmask-multi"), b(a.elmasks[a.activeMasksetIndex])) : b(a)
                        }, set: function (a, b) {
                            var e = d(a), h = c(a, b);
                            e.data("_inputmask-multi") && e.triggerHandler("setvalue");
                            return h
                        }, inputmaskmultipatch: !0}
                }
            }
            function l(a, b, c) {
                a = a.jquery && 0 < a.length ? a[0] : a;
                if ("number" == typeof b) {
                    b = w(b);
                    c = w(c);
                    c = "number" == typeof c ? c : b;
                    if (a != k) {
                        var e = d(a).data("_inputmask") || {};
                        e.caret = {begin: b, end: c};
                        d(a).data("_inputmask", e)
                    }
                    d(a).is(":visible") && (a.scrollLeft = a.scrollWidth, !1 == g.insertMode && b == c && c++, a.setSelectionRange ?
                            (a.selectionStart = b, a.selectionEnd = c) : a.createTextRange && (a = a.createTextRange(), a.collapse(!0), a.moveEnd("character", c), a.moveStart("character", b), a.select()))
                } else
                    return e = d(a).data("_inputmask"), !d(a).is(":visible") && e && void 0 != e.caret ? (b = e.caret.begin, c = e.caret.end) : a.setSelectionRange ? (b = a.selectionStart, c = a.selectionEnd) : document.selection && document.selection.createRange && (a = document.selection.createRange(), b = 0 - a.duplicate().moveStart("character", -1E5), c = b + a.text.length), b = w(b), c = w(c), {begin: b,
                        end: c}
            }
            function w(a) {
                !t || "number" != typeof a || g.greedy && "" == g.placeholder || (a = k.value.length - a);
                return a
            }
            function v(a, b) {
                if ("multiMaskScope" != a) {
                    if (d.isFunction(g.determineActiveMasksetIndex))
                        s = g.determineActiveMasksetIndex.call(n, a, b);
                    else {
                        var c = -1, e = -1, h = -1;
                        d.each(b, function (a, b) {
                            var f = d(b).data("_inputmask").maskset, g = -1, k = 0, n = l(b).begin, q;
                            for (q in f.validPositions)
                                f = parseInt(q), f > g && (g = f), k++;
                            if (k > c || k == c && e > n && h > g || k == c && e == n && h < g)
                                c = k, e = n, s = a, h = g
                        })
                    }
                    var f = n.data("_inputmask-multi") || {activeMasksetIndex: 0,
                        elmasks: b};
                    f.activeMasksetIndex = s;
                    n.data("_inputmask-multi", f)
                }
                -1 == d.inArray(a, ["focus"]) && k.value != b[s]._valueGet() && (f = "" == d(b[s]).val() ? b[s]._valueGet() : d(b[s]).val(), k.value = f);
                -1 == d.inArray(a, ["blur", "focus"]) && d(b[s]).hasClass("focus.inputmask") && (f = l(b[s]), l(k, f.begin, f.end))
            }
            function F(a) {
                k = a;
                n = d(k);
                t = "rtl" == k.dir || g.numericInput;
                s = 0;
                e = d.map(h, function (a, b) {
                    var c = '<input type="text" ';
                    n.attr("value") && (c += 'value="' + n.attr("value") + '" ');
                    n.attr("dir") && (c += 'dir="' + n.attr("dir") + '" ');
                    c = d(c +
                            "/>")[0];
                    d(c).inputmask(d.extend({}, g, {mask: a.mask}));
                    return c
                });
                n.data("_inputmask-multi", {activeMasksetIndex: 0, elmasks: e});
                ("rtl" == k.dir || g.rightAlign) && n.css("text-align", "right");
                k.dir = "ltr";
                n.removeAttr("dir");
                "" != n.attr("value") && v("init", e);
                n.bind("mouseenter blur focus mouseleave click dblclick keydown keypress keypress", function (a) {
                    var b = l(k), c, h = !0;
                    if ("keydown" == a.type) {
                        c = a.keyCode;
                        if (c == g.keyCode.DOWN && s < e.length - 1)
                            return s++, v("multiMaskScope", e), !1;
                        if (c == g.keyCode.UP && 0 < s)
                            return s--,
                                    v("multiMaskScope", e), !1;
                        if (a.ctrlKey || a.shiftKey || a.altKey)
                            return!0
                    } else if ("keypress" == a.type && (a.ctrlKey || a.shiftKey || a.altKey))
                        return!0;
                    d.each(e, function (e, f) {
                        if ("keydown" == a.type) {
                            c = a.keyCode;
                            if (c == g.keyCode.BACKSPACE && f._valueGet().length < b.begin)
                                return;
                            if (c == g.keyCode.TAB)
                                h = !1;
                            else {
                                if (c == g.keyCode.RIGHT) {
                                    l(f, b.begin + 1, b.end + 1);
                                    h = !1;
                                    return
                                }
                                if (c == g.keyCode.LEFT) {
                                    l(f, b.begin - 1, b.end - 1);
                                    h = !1;
                                    return
                                }
                            }
                        }
                        if (-1 != d.inArray(a.type, ["click"]) && (l(f, w(b.begin), w(b.end)), b.begin != b.end)) {
                            h = !1;
                            return
                        }
                        -1 !=
                                d.inArray(a.type, ["keydown"]) && b.begin != b.end && l(f, b.begin, b.end);
                        d(f).triggerHandler(a)
                    });
                    h && setTimeout(function () {
                        v(a.type, e)
                    }, 0)
                });
                n.bind(G + " dragdrop drop setvalue", function (a) {
                    l(k);
                    setTimeout(function () {
                        d.each(e, function (b, c) {
                            c._valueSet(k.value);
                            d(c).triggerHandler(a)
                        });
                        setTimeout(function () {
                            v(a.type, e)
                        }, 0)
                    }, 0)
                });
                f(k.type)
            }
            var G = b("paste") ? "paste" : b("input") ? "input" : "propertychange", t, k, n, e, s;
            g.multi = !0;
            if (void 0 != a)
                switch (a.action) {
                    case "isComplete":
                        return n = d(a.el), a = n.data("_inputmask-multi"),
                                a = a.elmasks[a.activeMasksetIndex], d(a).inputmask("isComplete");
                    case "unmaskedvalue":
                        return n = a.$input, a = n.data("_inputmask-multi"), a = a.elmasks[a.activeMasksetIndex], d(a).inputmask("unmaskedvalue");
                    case "mask":
                        F(a.el);
                        break;
                    case "format":
                        return n = d({}), n.data("_inputmask", {maskset: maskset, opts: g, isRTL: g.numericInput}), g.numericInput && (t = !0), a = a.value.split(""), checkVal(n, !1, !1, t ? a.reverse() : a, !0), t ? getBuffer().reverse().join("") : getBuffer().join("");
                    case "isValid":
                        return n = d({}), n.data("_inputmask",
                                {maskset: maskset, opts: g, isRTL: g.numericInput}), g.numericInput && (t = !0), a = a.value.split(""), checkVal(n, !1, !0, t ? a.reverse() : a), isComplete(getBuffer());
                    case "getemptymask":
                        return n = d(a.el), maskset = n.data("_inputmask").maskset, g = n.data("_inputmask").opts, getBufferTemplate();
                    case "remove":
                        k = a.el;
                        n = d(k);
                        maskset = n.data("_inputmask").maskset;
                        g = n.data("_inputmask").opts;
                        k._valueSet(unmaskedvalue(n));
                        n.unbind(".inputmask");
                        n.removeClass("focus.inputmask");
                        n.removeData("_inputmask");
                        var W;
                        Object.getOwnPropertyDescriptor &&
                                (W = Object.getOwnPropertyDescriptor(k, "value"));
                        W && W.get ? k._valueGet && Object.defineProperty(k, "value", {get: k._valueGet, set: k._valueSet}) : document.__lookupGetter__ && k.__lookupGetter__("value") && k._valueGet && (k.__defineGetter__("value", k._valueGet), k.__defineSetter__("value", k._valueSet));
                        try {
                            delete k._valueGet, delete k._valueSet
                        } catch (pa) {
                            k._valueGet = void 0, k._valueSet = void 0
                        }
                    }
        };
        d.extend(d.inputmask.defaults, {multi: !1, nojumps: !1, nojumpsThreshold: 0, determineActiveMasksetIndex: void 0});
        d.inputmask._fn =
                d.fn.inputmask;
        d.fn.inputmask = function (c, h) {
            if ("string" === typeof c)
                return d.inputmask._fn("_detectScope", h, void 0, void 0, c) ? d.inputmask._fn.call(this, c, h, a, "_inputmask-multi") : d.inputmask._fn.call(this, c, h);
            if ("object" == typeof c)
                return d.inputmask._fn("_detectScope", c) ? d.inputmask._fn.call(this, c, h, a, "_inputmask-multi") : d.inputmask._fn.call(this, c, h);
            if (void 0 == c)
                return d.inputmask._fn.call(this, c, h)
        }
    }
})(jQuery);
(function (d) {
    d.extend(d.inputmask.defaults.definitions, {A: {validator: "[A-Za-z]", cardinality: 1, casing: "upper"}, "#": {validator: "[A-Za-z\u0410-\u044f\u0401\u04510-9]", cardinality: 1, casing: "upper"}});
    d.extend(d.inputmask.defaults.aliases, {url: {mask: "ir", placeholder: "", separator: "", defaultPrefix: "http://", regex: {urlpre1: /[fh]/, urlpre2: /(ft|ht)/, urlpre3: /(ftp|htt)/, urlpre4: /(ftp:|http|ftps)/, urlpre5: /(ftp:\/|ftps:|http:|https)/, urlpre6: /(ftp:\/\/|ftps:\/|http:\/|https:)/, urlpre7: /(ftp:\/\/|ftps:\/\/|http:\/\/|https:\/)/,
                urlpre8: /(ftp:\/\/|ftps:\/\/|http:\/\/|https:\/\/)/}, definitions: {i: {validator: function (a, c, h, d, b) {
                        return!0
                    }, cardinality: 8, prevalidator: function () {
                        for (var a = [], c = 0; 8 > c; c++)
                            a[c] = function () {
                                var a = c;
                                return{validator: function (c, b, d, l, w) {
                                        if (w.regex["urlpre" + (a + 1)]) {
                                            var v = c;
                                            0 < a + 1 - c.length && (v = b.join("").substring(0, a + 1 - c.length) + "" + v);
                                            c = w.regex["urlpre" + (a + 1)].test(v);
                                            if (!l && !c) {
                                                d -= a;
                                                for (l = 0; l < w.defaultPrefix.length; l++)
                                                    b[d] = w.defaultPrefix[l], d++;
                                                for (l = 0; l < v.length - 1; l++)
                                                    b[d] = v[l], d++;
                                                return{pos: d}
                                            }
                                            return c
                                        }
                                        return!1
                                    },
                                    cardinality: a}
                            }();
                        return a
                    }()}, r: {validator: ".", cardinality: 50}}, insertMode: !1, autoUnmask: !1}, ip: {mask: "i[i[i]].i[i[i]].i[i[i]].i[i[i]]", definitions: {i: {validator: function (a, c, d, g, b) {
                        -1 < d - 1 && "." != c[d - 1] ? (a = c[d - 1] + a, a = -1 < d - 2 && "." != c[d - 2] ? c[d - 2] + a : "0" + a) : a = "00" + a;
                        return/25[0-5]|2[0-4][0-9]|[01][0-9][0-9]/.test(a)
                    }, cardinality: 1}}}, email: {mask: "*{1,20}[.*{1,20}][.*{1,20}][.*{1,20}]@*{1,20}.*{2,6}[.*{1,2}]", greedy: !1, onBeforePaste: function (a, c) {
                a = a.toLowerCase();
                return a.replace("mailto:", "")
            }, definitions: {"*": {validator: "[A-Za-z\u0410-\u044f\u0401\u04510-9]",
                    cardinality: 1, casing: "lower"}}}})
})(jQuery);
(function (d) {
    d.extend(d.inputmask.defaults.definitions, {h: {validator: "[01][0-9]|2[0-3]", cardinality: 2, prevalidator: [{validator: "[0-2]", cardinality: 1}]}, s: {validator: "[0-5][0-9]", cardinality: 2, prevalidator: [{validator: "[0-5]", cardinality: 1}]}, d: {validator: "0[1-9]|[12][0-9]|3[01]", cardinality: 2, prevalidator: [{validator: "[0-3]", cardinality: 1}]}, m: {validator: "0[1-9]|1[012]", cardinality: 2, prevalidator: [{validator: "[01]", cardinality: 1}]}, y: {validator: "(19|20)\\d{2}", cardinality: 4, prevalidator: [{validator: "[12]",
                    cardinality: 1}, {validator: "(19|20)", cardinality: 2}, {validator: "(19|20)\\d", cardinality: 3}]}});
    d.extend(d.inputmask.defaults.aliases, {"dd/mm/yyyy": {mask: "1/2/y", placeholder: "dd/mm/yyyy", regex: {val1pre: /[0-3]/, val1: /0[1-9]|[12][0-9]|3[01]/, val2pre: function (a) {
                    a = d.inputmask.escapeRegex.call(this, a);
                    return RegExp("((0[1-9]|[12][0-9]|3[01])" + a + "[01])")
                }, val2: function (a) {
                    a = d.inputmask.escapeRegex.call(this, a);
                    return RegExp("((0[1-9]|[12][0-9])" + a + "(0[1-9]|1[012]))|(30" + a + "(0[13-9]|1[012]))|(31" + a + "(0[13578]|1[02]))")
                }},
            leapday: "29/02/", separator: "/", yearrange: {minyear: 1900, maxyear: 2099}, isInYearRange: function (a, c, d) {
                if (isNaN(a))
                    return!1;
                var g = parseInt(a.concat(c.toString().slice(a.length)));
                a = parseInt(a.concat(d.toString().slice(a.length)));
                return(isNaN(g) ? !1 : c <= g && g <= d) || (isNaN(a) ? !1 : c <= a && a <= d)
            }, determinebaseyear: function (a, c, d) {
                var g = (new Date).getFullYear();
                if (a > g)
                    return a;
                if (c < g) {
                    for (var g = c.toString().slice(0, 2), b = c.toString().slice(2, 4); c < g + d; )
                        g--;
                    c = g + b;
                    return a > c ? a : c
                }
                return g
            }, onKeyUp: function (a, c, h) {
                c =
                        d(this);
                a.ctrlKey && a.keyCode == h.keyCode.RIGHT && (a = new Date, c.val(a.getDate().toString() + (a.getMonth() + 1).toString() + a.getFullYear().toString()))
            }, definitions: {1: {validator: function (a, c, d, g, b) {
                        var f = b.regex.val1.test(a);
                        return g || f || a.charAt(1) != b.separator && -1 == "-./".indexOf(a.charAt(1)) || !(f = b.regex.val1.test("0" + a.charAt(0))) ? f : (c[d - 1] = "0", {refreshFromBuffer: {start: d - 1, end: d}, pos: d, c: a.charAt(0)})
                    }, cardinality: 2, prevalidator: [{validator: function (a, c, d, g, b) {
                                isNaN(c[d + 1]) || (a += c[d + 1]);
                                var f = 1 == a.length ?
                                        b.regex.val1pre.test(a) : b.regex.val1.test(a);
                                return g || f || !(f = b.regex.val1.test("0" + a)) ? f : (c[d] = "0", d++, {pos: d})
                            }, cardinality: 1}]}, 2: {validator: function (a, c, d, g, b) {
                        var f = b.mask.indexOf("2") == b.mask.length - 1 ? c.join("").substr(5, 3) : c.join("").substr(0, 3);
                        -1 != f.indexOf(b.placeholder[0]) && (f = "01" + b.separator);
                        var l = b.regex.val2(b.separator).test(f + a);
                        if (!(g || l || a.charAt(1) != b.separator && -1 == "-./".indexOf(a.charAt(1))) && (l = b.regex.val2(b.separator).test(f + "0" + a.charAt(0))))
                            return c[d - 1] = "0", {refreshFromBuffer: {start: d -
                                    1, end: d}, pos: d, c: a.charAt(0)};
                        if (b.mask.indexOf("2") == b.mask.length - 1 && l) {
                            if (c.join("").substr(4, 4) + a != b.leapday)
                                return!0;
                            a = parseInt(c.join("").substr(0, 4), 10);
                            return 0 === a % 4 ? 0 === a % 100 ? 0 === a % 400 ? !0 : !1 : !0 : !1
                        }
                        return l
                    }, cardinality: 2, prevalidator: [{validator: function (a, c, d, g, b) {
                                isNaN(c[d + 1]) || (a += c[d + 1]);
                                var f = b.mask.indexOf("2") == b.mask.length - 1 ? c.join("").substr(5, 3) : c.join("").substr(0, 3);
                                -1 != f.indexOf(b.placeholder[0]) && (f = "01" + b.separator);
                                var l = 1 == a.length ? b.regex.val2pre(b.separator).test(f + a) :
                                        b.regex.val2(b.separator).test(f + a);
                                return g || l || !(l = b.regex.val2(b.separator).test(f + "0" + a)) ? l : (c[d] = "0", d++, {pos: d})
                            }, cardinality: 1}]}, y: {validator: function (a, c, d, g, b) {
                        if (b.isInYearRange(a, b.yearrange.minyear, b.yearrange.maxyear)) {
                            if (c.join("").substr(0, 6) != b.leapday)
                                return!0;
                            a = parseInt(a, 10);
                            return 0 === a % 4 ? 0 === a % 100 ? 0 === a % 400 ? !0 : !1 : !0 : !1
                        }
                        return!1
                    }, cardinality: 4, prevalidator: [{validator: function (a, c, d, g, b) {
                                var f = b.isInYearRange(a, b.yearrange.minyear, b.yearrange.maxyear);
                                if (!g && !f) {
                                    g = b.determinebaseyear(b.yearrange.minyear,
                                            b.yearrange.maxyear, a + "0").toString().slice(0, 1);
                                    if (f = b.isInYearRange(g + a, b.yearrange.minyear, b.yearrange.maxyear))
                                        return c[d++] = g[0], {pos: d};
                                    g = b.determinebaseyear(b.yearrange.minyear, b.yearrange.maxyear, a + "0").toString().slice(0, 2);
                                    if (f = b.isInYearRange(g + a, b.yearrange.minyear, b.yearrange.maxyear))
                                        return c[d++] = g[0], c[d++] = g[1], {pos: d}
                                }
                                return f
                            }, cardinality: 1}, {validator: function (a, c, d, g, b) {
                                var f = b.isInYearRange(a, b.yearrange.minyear, b.yearrange.maxyear);
                                if (!g && !f) {
                                    g = b.determinebaseyear(b.yearrange.minyear,
                                            b.yearrange.maxyear, a).toString().slice(0, 2);
                                    if (f = b.isInYearRange(a[0] + g[1] + a[1], b.yearrange.minyear, b.yearrange.maxyear))
                                        return c[d++] = g[1], {pos: d};
                                    g = b.determinebaseyear(b.yearrange.minyear, b.yearrange.maxyear, a).toString().slice(0, 2);
                                    b.isInYearRange(g + a, b.yearrange.minyear, b.yearrange.maxyear) ? c.join("").substr(0, 6) != b.leapday ? f = !0 : (b = parseInt(a, 10), f = 0 === b % 4 ? 0 === b % 100 ? 0 === b % 400 ? !0 : !1 : !0 : !1) : f = !1;
                                    if (f)
                                        return c[d - 1] = g[0], c[d++] = g[1], c[d++] = a[0], {refreshFromBuffer: {start: d - 3, end: d}, pos: d}
                                }
                                return f
                            },
                            cardinality: 2}, {validator: function (a, c, d, g, b) {
                                return b.isInYearRange(a, b.yearrange.minyear, b.yearrange.maxyear)
                            }, cardinality: 3}]}}, insertMode: !1, autoUnmask: !1}, "mm/dd/yyyy": {placeholder: "mm/dd/yyyy", alias: "dd/mm/yyyy", regex: {val2pre: function (a) {
                    a = d.inputmask.escapeRegex.call(this, a);
                    return RegExp("((0[13-9]|1[012])" + a + "[0-3])|(02" + a + "[0-2])")
                }, val2: function (a) {
                    a = d.inputmask.escapeRegex.call(this, a);
                    return RegExp("((0[1-9]|1[012])" + a + "(0[1-9]|[12][0-9]))|((0[13-9]|1[012])" + a + "30)|((0[13578]|1[02])" +
                            a + "31)")
                }, val1pre: /[01]/, val1: /0[1-9]|1[012]/}, leapday: "02/29/", onKeyUp: function (a, c, h) {
                c = d(this);
                a.ctrlKey && a.keyCode == h.keyCode.RIGHT && (a = new Date, c.val((a.getMonth() + 1).toString() + a.getDate().toString() + a.getFullYear().toString()))
            }}, "yyyy/mm/dd": {mask: "y/1/2", placeholder: "yyyy/mm/dd", alias: "mm/dd/yyyy", leapday: "/02/29", onKeyUp: function (a, c, h) {
                c = d(this);
                a.ctrlKey && a.keyCode == h.keyCode.RIGHT && (a = new Date, c.val(a.getFullYear().toString() + (a.getMonth() + 1).toString() + a.getDate().toString()))
            }}, "dd.mm.yyyy": {mask: "1.2.y",
            placeholder: "dd.mm.yyyy", leapday: "29.02.", separator: ".", alias: "dd/mm/yyyy"}, "dd-mm-yyyy": {mask: "1-2-y", placeholder: "dd-mm-yyyy", leapday: "29-02-", separator: "-", alias: "dd/mm/yyyy"}, "mm.dd.yyyy": {mask: "1.2.y", placeholder: "mm.dd.yyyy", leapday: "02.29.", separator: ".", alias: "mm/dd/yyyy"}, "mm-dd-yyyy": {mask: "1-2-y", placeholder: "mm-dd-yyyy", leapday: "02-29-", separator: "-", alias: "mm/dd/yyyy"}, "yyyy.mm.dd": {mask: "y.1.2", placeholder: "yyyy.mm.dd", leapday: ".02.29", separator: ".", alias: "yyyy/mm/dd"}, "yyyy-mm-dd": {mask: "y-1-2",
            placeholder: "yyyy-mm-dd", leapday: "-02-29", separator: "-", alias: "yyyy/mm/dd"}, datetime: {mask: "1/2/y h:s", placeholder: "dd/mm/yyyy hh:mm", alias: "dd/mm/yyyy", regex: {hrspre: /[012]/, hrs24: /2[0-4]|1[3-9]/, hrs: /[01][0-9]|2[0-4]/, ampm: /^[a|p|A|P][m|M]/}, timeseparator: ":", hourFormat: "24", definitions: {h: {validator: function (a, c, d, g, b) {
                        if ("24" == b.hourFormat && 24 == parseInt(a, 10))
                            return c[d - 1] = "0", c[d] = "0", {refreshFromBuffer: {start: d - 1, end: d}, c: "0"};
                        var f = b.regex.hrs.test(a);
                        return g || f || a.charAt(1) != b.timeseparator &&
                                -1 == "-.:".indexOf(a.charAt(1)) || !(f = b.regex.hrs.test("0" + a.charAt(0))) ? f && "24" !== b.hourFormat && b.regex.hrs24.test(a) ? (a = parseInt(a, 10), c[d + 5] = 24 == a ? "a" : "p", c[d + 6] = "m", a -= 12, 10 > a ? (c[d] = a.toString(), c[d - 1] = "0") : (c[d] = a.toString().charAt(1), c[d - 1] = a.toString().charAt(0)), {refreshFromBuffer: {start: d - 1, end: d + 6}, c: c[d]}) : f : (c[d - 1] = "0", c[d] = a.charAt(0), d++, {refreshFromBuffer: {start: d - 2, end: d}, pos: d, c: b.timeseparator})
                    }, cardinality: 2, prevalidator: [{validator: function (a, c, d, g, b) {
                                var f = b.regex.hrspre.test(a);
                                return g || f || !(f = b.regex.hrs.test("0" + a)) ? f : (c[d] = "0", d++, {pos: d})
                            }, cardinality: 1}]}, t: {validator: function (a, c, d, g, b) {
                        return b.regex.ampm.test(a + "m")
                    }, casing: "lower", cardinality: 1}}, insertMode: !1, autoUnmask: !1}, datetime12: {mask: "1/2/y h:s t\\m", placeholder: "dd/mm/yyyy hh:mm xm", alias: "datetime", hourFormat: "12"}, "hh:mm t": {mask: "h:s t\\m", placeholder: "hh:mm xm", alias: "datetime", hourFormat: "12"}, "h:s t": {mask: "h:s t\\m", placeholder: "hh:mm xm", alias: "datetime", hourFormat: "12"}, "hh:mm:ss": {mask: "h:s:s",
            autoUnmask: !1}, "hh:mm": {mask: "h:s", autoUnmask: !1}, date: {alias: "dd/mm/yyyy"}, "mm/yyyy": {mask: "1/y", placeholder: "mm/yyyy", leapday: "donotuse", separator: "/", alias: "mm/dd/yyyy"}})
})(jQuery);
(function (d) {
    d.extend(d.inputmask.defaults.aliases, {numeric: {mask: function (a) {
                0 !== a.repeat && isNaN(a.integerDigits) && (a.integerDigits = a.repeat);
                a.repeat = 0;
                a.autoGroup = a.autoGroup && "" != a.groupSeparator;
                if (a.autoGroup && isFinite(a.integerDigits)) {
                    var c = Math.floor(a.integerDigits / a.groupSize);
                    a.integerDigits += 0 == a.integerDigits % a.groupSize ? c - 1 : c
                }
                c = a.prefix;
                c = c + "[+]" + ("~{1," + a.integerDigits + "}");
                void 0 != a.digits && (isNaN(a.digits) || 0 < parseInt(a.digits)) && (c = a.digitsOptional ? c + ("[" + a.radixPoint + "~{" + a.digits +
                        "}]") : c + (a.radixPoint + "~{" + a.digits + "}"));
                return c += a.suffix
            }, placeholder: "", greedy: !1, digits: "*", digitsOptional: !0, groupSeparator: "", radixPoint: ".", groupSize: 3, autoGroup: !1, allowPlus: !0, allowMinus: !0, integerDigits: "+", prefix: "", suffix: "", skipRadixDance: !1, getLastValidPosition: function (a, c, h) {
                var g = -1, b = a.validPositions, f;
                for (f in b)
                    b = parseInt(f), b > g && (g = b);
                void 0 != c && (a = a.buffer, !1 === h.skipRadixDance && "" != h.radixPoint && -1 != d.inArray(h.radixPoint, a) && (g = d.inArray(h.radixPoint, a)));
                return g
            }, rightAlign: !0,
            postFormat: function (a, c, h, g) {
                var b = !1;
                if ("" == g.groupSeparator || -1 != d.inArray(g.radixPoint, a) && c >= d.inArray(g.radixPoint, a))
                    return{pos: c};
                var f = a.slice();
                h || f.splice(c, 0, "?");
                f = f.join("");
                if (g.autoGroup || h && -1 != f.indexOf(g.groupSeparator)) {
                    var l = d.inputmask.escapeRegex.call(this, g.groupSeparator), f = f.replace(RegExp(l, "g"), ""), l = f.split(g.radixPoint), f = l[0];
                    if (f != g.prefix + "?0")
                        for (var b = !0, w = RegExp("([-+]?[\\d?]+)([\\d?]{" + g.groupSize + "})"); w.test(f); )
                            f = f.replace(w, "$1" + g.groupSeparator + "$2"), f = f.replace(g.groupSeparator +
                                    g.groupSeparator, g.groupSeparator);
                    1 < l.length && (f += g.radixPoint + l[1])
                }
                a.length = f.length;
                g = 0;
                for (l = f.length; g < l; g++)
                    a[g] = f.charAt(g);
                f = d.inArray("?", a);
                h || a.splice(f, 1);
                return{pos: h ? c : f, refreshFromBuffer: b}
            }, onKeyDown: function (a, c, h) {
                d(this);
                if (h.autoGroup && a.keyCode == h.keyCode.DELETE || a.keyCode == h.keyCode.BACKSPACE)
                    return h.postFormat(c, 0, !0, h)
            }, regex: {integerPart: function (a) {
                    return/[-+]?\d+/
                }}, negationhandler: function (a, c, d, g, b) {
                return!g && b.allowMinus && "-" === a && (a = c.join("").match(b.regex.integerPart(b)),
                        0 < a.length) ? "+" == c[a.index] ? (c.splice(a.index, 1), {pos: a.index, c: "-", refreshFromBuffer: !0, caret: d}) : "-" == c[a.index] ? (c.splice(a.index, 1), {refreshFromBuffer: !0, caret: d - 1}) : {pos: a.index, c: "-", caret: d + 1} : !1
            }, definitions: {"~": {validator: function (a, c, h, g, b) {
                        var f = b.negationhandler(a, c, h, g, b);
                        if (!f) {
                            f = g ? RegExp("[0-9" + d.inputmask.escapeRegex.call(this, b.groupSeparator) + "]").test(a) : /[0-9]/.test(a);
                            if (!1 != f) {
                                var l = c.join("").match(b.regex.integerPart(b)), w = d.inArray(b.radixPoint, c);
                                if (l && "0" == l["0"][0] && h >=
                                        b.prefix.length && (-1 == w || h < w))
                                    c.splice(l.index, 1);
                                else if ("0" == a && l && 0 < l["0"].length && h == b.prefix.length)
                                    return!1
                            }
                            !1 == f || g || a == b.radixPoint || !0 !== b.autoGroup || (f = b.postFormat(c, h, "-" == a || "+" == a ? !0 : !1, b))
                        }
                        return f
                    }, cardinality: 1, prevalidator: null}, "+": {validator: function (a, c, d, g, b) {
                        c = "[";
                        !0 === b.allowMinus && (c += "-");
                        !0 === b.allowPlus && (c += "+");
                        return RegExp(c + "]").test(a)
                    }, cardinality: 1, prevalidator: null}}, insertMode: !0, autoUnmask: !1, onUnMask: function (a, c, d) {
                return c
            }}, decimal: {alias: "numeric"}, integer: {alias: "numeric",
            digits: "0"}})
})(jQuery);
(function (d) {
    d.extend(d.inputmask.defaults.aliases, {Regex: {mask: "r", greedy: !1, repeat: "*", regex: null, regexTokens: null, tokenizer: /\[\^?]?(?:[^\\\]]+|\\[\S\s]?)*]?|\\(?:0(?:[0-3][0-7]{0,2}|[4-7][0-7]?)?|[1-9][0-9]*|x[0-9A-Fa-f]{2}|u[0-9A-Fa-f]{4}|c[A-Za-z]|[\S\s]?)|\((?:\?[:=!]?)?|(?:[?*+]|\{[0-9]+(?:,[0-9]*)?\})\??|[^.?*+^${[()|\\]+|./g, quantifierFilter: /[0-9]+[^,]/, isComplete: function (a, c) {
                return RegExp(c.regex).test(a.join(""))
            }, definitions: {r: {validator: function (a, c, h, g, b) {
                        function f(a, b) {
                            this.matches =
                            [];
                            this.isGroup = a || !1;
                            this.isQuantifier = b || !1;
                            this.quantifier = {min: 1, max: 1};
                            this.repeaterPart = void 0
                        }
                        function l() {
                            var a = new f, c, d = [];
                            for (b.regexTokens = []; c = b.tokenizer.exec(b.regex); )
                                switch (c = c[0], c.charAt(0)) {
                                    case "(":
                                        d.push(new f(!0));
                                        break;
                                    case ")":
                                        var e = d.pop();
                                        0 < d.length ? d[d.length - 1].matches.push(e) : a.matches.push(e);
                                        break;
                                    case "{":
                                    case "+":
                                    case "*":
                                        var g = new f(!1, !0);
                                        c = c.replace(/[{}]/g, "");
                                        e = c.split(",");
                                        c = isNaN(e[0]) ? e[0] : parseInt(e[0]);
                                        e = 1 == e.length ? c : isNaN(e[1]) ? e[1] : parseInt(e[1]);
                                        g.quantifier =
                                                {min: c, max: e};
                                        if (0 < d.length) {
                                            var h = d[d.length - 1].matches;
                                            c = h.pop();
                                            c.isGroup || (e = new f(!0), e.matches.push(c), c = e);
                                            h.push(c);
                                            h.push(g)
                                        } else
                                            c = a.matches.pop(), c.isGroup || (e = new f(!0), e.matches.push(c), c = e), a.matches.push(c), a.matches.push(g);
                                        break;
                                    default:
                                        0 < d.length ? d[d.length - 1].matches.push(c) : a.matches.push(c)
                                }
                            0 < a.matches.length && b.regexTokens.push(a)
                        }
                        function w(a, b) {
                            var c = !1;
                            b && (v += "(", F++);
                            for (var e = 0; e < a.matches.length; e++) {
                                var f = a.matches[e];
                                if (!0 == f.isGroup)
                                    c = w(f, !0);
                                else if (!0 == f.isQuantifier) {
                                    var g =
                                            d.inArray(f, a.matches), g = a.matches[g - 1], h = v;
                                    if (isNaN(f.quantifier.max)) {
                                        for (; f.repeaterPart && f.repeaterPart != v && f.repeaterPart.length > v.length && !(c = w(g, !0)); )
                                            ;
                                        (c = c || w(g, !0)) && (f.repeaterPart = v);
                                        v = h + f.quantifier.max
                                    } else {
                                        for (var l = 0, q = f.quantifier.max - 1; l < q && !(c = w(g, !0)); l++)
                                            ;
                                        v = h + "{" + f.quantifier.min + "," + f.quantifier.max + "}"
                                    }
                                } else if (void 0 != f.matches)
                                    for (g = 0; g < f.length && !(c = w(f[g], b)); g++)
                                        ;
                                else {
                                    if ("[" == f[0]) {
                                        c = v;
                                        c += f;
                                        for (l = 0; l < F; l++)
                                            c += ")";
                                        c = RegExp("^(" + c + ")$");
                                        c = c.test(G)
                                    } else
                                        for (g = 0, h = f.length; g <
                                                h; g++)
                                            if ("\\" != f[g]) {
                                                c = v;
                                                c += f.substr(0, g + 1);
                                                c = c.replace(/\|$/, "");
                                                for (l = 0; l < F; l++)
                                                    c += ")";
                                                c = RegExp("^(" + c + ")$");
                                                if (c = c.test(G))
                                                    break
                                            }
                                    v += f
                                }
                                if (c)
                                    break
                            }
                            b && (v += ")", F--);
                            return c
                        }
                        null == b.regexTokens && l();
                        g = c.slice();
                        var v = "";
                        c = !1;
                        var F = 0;
                        g.splice(h, 0, a);
                        var G = g.join("");
                        for (a = 0; a < b.regexTokens.length && !(f = b.regexTokens[a], c = w(f, f.isGroup)); a++)
                            ;
                        return c
                    }, cardinality: 1}}}})
})(jQuery);
(function (d) {
    d.extend(d.inputmask.defaults.aliases, {phone: {url: "phone-codes/phone-codes.json", mask: function (a) {
                a.definitions = {p: {validator: function () {
                            return!1
                        }, cardinality: 1}, "#": {validator: "[0-9]", cardinality: 1}};
                var c = [];
                d.ajax({url: a.url, async: !1, dataType: "json", success: function (a) {
                        c = a
                    }});
                c.splice(0, 0, "+p(ppp)ppp-pppp");
                return c
            }, nojumps: !0, nojumpsThreshold: 1}, phonebe: {url: "phone-codes/phone-be.json", mask: function (a) {
                a.definitions = {p: {validator: function () {
                            return!1
                        }, cardinality: 1}, "#": {validator: "[0-9]",
                        cardinality: 1}};
                var c = [];
                d.ajax({url: a.url, async: !1, dataType: "json", success: function (a) {
                        c = a
                    }});
                c.splice(0, 0, "+32(ppp)ppp-pppp");
                return c
            }, nojumps: !0, nojumpsThreshold: 4}})
})(jQuery);
