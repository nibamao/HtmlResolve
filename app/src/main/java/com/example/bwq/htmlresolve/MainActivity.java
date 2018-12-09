package com.example.bwq.htmlresolve;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.example.bwq.html_resolve.HtmlSpanner;
import com.example.bwq.html_resolve.LinkMovementMethodExt;

public class MainActivity extends AppCompatActivity {

    private String html = "<div class=\"text-title\" style=\"font-size:12px;border-top:0px;font-family:&quot;pingfang sc&quot;, arial, 微软雅黑, 宋体, " +
            "simsun, sans-serif;border-right:0px;word-spacing:0px;border-bottom:0px;position:relative;text-transform:none;font-weight:normal;color:#666666;font-style:normal;" +
            "border-left:0px;orphans:2;widows:2;margin:0px;letter-spacing:normal;top:-6px;background-color:#ffffff;text-indent:0px;font-variant-ligatures:normal;font-variant-caps:normal;-webkit-text-stroke-width:" +
            "0px;text-decoration-style:initial;text-decoration-color:initial;padding:0px;\"><h1 style=\"font-size:28px;border-top:0px;border-right:0px;border-bottom:0px;font-weight:700;color:#191919;border-left:" +
            "0px;margin:0px;line-height:38px;padding:0px;\">湖北莲产业发展联盟在洪湖成立 助推千湖之省莲业扶贫<span class=\"article-tag\" style=\"font-size:28px;border-top:0px;border-right:0px;border-bottom:0px;" +
            "position:relative;font-weight:400;border-left:0px;margin:0px;display:inline-block;top:-4px;padding:0px;\"></span></h1><div class=\"article-info\" style=\"font-size:14px;border-top:0px;border-right" +
            ":0px;border-bottom:0px;color:#999999;border-left:0px;margin:0px;line-height:20px;padding:15px 0px 0px;\"><span id=\"news-time\" class=\"time\" style=\"font-size:14px;border-top:0px;border-right:" +
            "0px;border-bottom:0px;border-left:0px;margin:0px;padding:0px;\" data-val=\"1540629124000\">2018-10-27 16:32</span><span class=\"tag\" style=\"font-size:14px;border-top:0px;border-right:0px;" +
            "background:url(data:image/png;base64,ivborw0kggoaaaansuheugaaaa8aaaascayaaacenoqpaaaagxrfwhrtb2z0d2fyzqbbzg9izsbjbwfnzvjlywr5ccllpaaaayjpvfh0we1momnvbs5hzg9izs54bxaaaaaaadw/ehbhy2tldcbizwdpbj0i77u/iibpzd0ivzvnme1wq2voauh6cmvtek5uy3" +
            "pryzlkij8+idx4onhtcg1ldgegeg1sbnm6ed0iywrvymu6bnm6bwv0ys8iihg6eg1wdgs9ikfkb2jlifhnucbdb3jliduumy1jmdexidy2lje0nty2mswgmjaxmi8wmi8wni0xndo1njoynyagicagicagij4gphjkzjpsreygeg1sbnm6cmrmpsjodhrwoi8vd3d3lnczlm9yzy8xotk5lzaylziylxjkzi1zew50yxgtbnmjij4gphjkzjpezxnjcmlwdglvbibyzgy6ywjvdxq9iiigeg1sbnm6eg1wpsjodhrwoi8vbnmuywrvymuuy29tl3hhcc8xljaviib4bwxuczp4bxbntt0iahr0cdovl25zlmfkb2jllmnvbs94yxavms4wl21tlyigeg1sbnm6c3rszwy9imh0dha6ly9ucy5hzg9izs5jb20vegfwlzeumc9zvhlwzs9szxnvdxjjzvjlzimiihhtcdpdcmvhdg9yvg9vbd0iqwrvymugughvdg9zag9wientniaov2luzg93cykiihhtce1nokluc3rhbmnlsuq9inhtcc5pawq6n0y2qjk4quu3mtezmtffnzlbqzy4mtyynjbfodlcmzuiihhtce1nokrvy3vtzw50suq9inhtcc5kawq6n0y2qjk4quy3mtezmtffnzlbqzy4mtyynjbfodlcmzuipia8eg1wtu06rgvyaxzlzezyb20gc3rszwy6aw5zdgfuy2vjrd0ieg1wlmlpzdo3rjzcothbqzcxmtmxmuu3oufdnjgxnji2meu4ouiznsigc3rszwy6zg9jdw1lbnrjrd0ieg1wlmrpzdo3rjzcothbrdcxmtmxmuu3oufdnjgxnji2meu4ouiznsivpia8l3jkzjpezxnjcmlwdglvbj4gpc9yzgy6ukrgpia8l3g6eg1wbwv0yt4gpd94cgfja2v0igvuzd0icii/pikssmeaaajasurbvhjaffpni1jrfd/v8fbzfbwt/age0ccf+aeugjyh/rvbmzyd0rrtdvolgtbniqkhokh9ubeerrailwovmoiitsvuukthfl9b+53xm8eojw78opece373no97hcviqvqstqd3otyba/atqag/gb/ak1qq9vvqioo4b7vadykrcimisjpjrmv4pkz6vu7t6dqlardv2wy27wg0spl0b1un05hfylfj5vm5vsqvi+ik8b7d4dioxwiqsdpp0gg0wrrayobkyc8diz5wop1br6mylmtlgg6hmtnr9zlf75f9rqnrjosk8ahl5voi8o1mjmfjlegkqfarrd4clkdcc7vdl8phmamcoaayzwyadabu6/wo2+0sdlf3lju9fm1mmbhcirlo9xokbajy6txhn8+n7vfhkd9mfpzl5wg2m62nzopxudkzpfaorcatsfa1223k5/o8pgtyt36/p8tkmjw7+p+0wi2ziffdgrkhkq8ohfr2+abouusajqyvcgumnof5enawuqgcxho7g+7uzrpztbnyiyowi4txebbmr+azlyvxddxh1y/ggh0qb3ag3gmwarvkpvkjiqmyx4bxxafid+s3deizod5xcxa+hx0ft/labrdts9nkwx8b34hx3cpexowd1y8bqhtqk3anmwewt2b9dusxwg9g/rbiwr5+vm7sr0lqaagtzhzbdxwfdeo98bm+m8d7sfylexwkiv/yozluo+adildwjycuohbuhbon8myywbstistfaqyanicokto4gqgaaaaasuvork5cyii=) no-repeat 0px 0px;border-bottom:0px;float:right;border-left:0px;margin:0px;padding:0px 0px 0px 25px;\"><a style=\"font-size:14px;text-decoration:none;border-top:0px;border-right:0px;border-bottom:0px;float:left;color:#539ff3;border-left:0px;margin:0px;background-color:transparent;padding:0px;\" href=\"http://www.sohu.com/tag/69962\" target=\"_blank\">扶贫</a><em style=\"font-size:14px;border-top:0px;border-right:0px;border-bottom:0px;float:left;color:#dfdfdf;font-style:normal;border-left:0px;margin:0px 6px;padding:0px;\">/</em><a style=\"font-size:14px;text-decoration:none;border-top:0px;border-right:0px;border-bottom:0px;float:left;color:#539ff3;border-left:0px;margin:0px;background-color:transparent;padding:0px;\" href=\"http://www.sohu.com/tag/78029\" target=\"_blank\">技术</a><em style=\"font-size:14px;border-top:0px;border-right:0px;border-bottom:0px;float:left;color:#dfdfdf;font-style:normal;border-left:0px;margin:0px 6px;padding:0px;\">/</em>" +
            "<a style=\"font-size:14px;text-decoration:none;border-top:0px;border-right:0px;border-bottom:0px;float:left;color:#539ff3;border-left:0px;margin:0px;background-color:transparent;padding:0px;" +
            "\" href=\"http://www.sohu.com/tag/78028\" target=\"_blank\">开发</a></span></div></div><article id=\"mp-editor\" class=\"article\" style=\"box-sizing:border-box;font-size:16px;word-wrap:break-word;border-top:0px;font-family" +
            ":&quot;pingfang sc&quot;, arial, 微软雅黑, 宋体, simsun, sans-serif;border-right:0px;word-spacing:0px;overflow-x:hidden;overflow-y:auto;border-bottom:0px;text-transform:none;font-weight:normal;color:#191919;outline-width:" +
            "0px;font-style:normal;text-align:left;outline-style:none;border-left:0px;orphans:2;widows:2;margin:0px;display:block;letter-spacing:normal;outline-color:invert;line-height:1.9;background-color:#ffffff;text-indent:0px;font-" +
            "variant-ligatures:normal;font-variant-caps:normal;-webkit-text-stroke-width:0px;text-decoration-style:initial;text-decoration-color:initial;tab-size:4;padding:5px 0px 0px;\"><p class=\"ql-align-center\" style=\"font-size:" +
            "16px;border-top:0px;border-right:0px;border-bottom:0px;text-align:center;border-left:0px;margin:0.63em 0px 1.8em;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;padding:0px;\">" +
            "<img style=\"font-size:16px;max-width:100%;border-top:0px;height:auto;border-right:0px;border-bottom:0px;border-left:0px;margin:10px auto 0px;display:block;padding:0px;\" " +
            "src=\"/blogimg/asset/2016/bg2016072208.png\" max-width=\"600\" /></p>" +
            "<p style=\"font-size:16px;border-top:0px;border-right:0px;border-bottom:0px;border-left:0px;margin:0.63em 0px 1.8em;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;padding:0px;\">" +
            "人民日报《人民周刊》湖北洪湖讯（楚予统筹报道）10月26日，湖北省莲产业发展联盟在洪湖市成立，全省各地近200家从事莲产业种植、加工、流通、储运的企业及从事技术研发、推广的科研院所等单位成为联盟会员。该联盟的成立，是湖北省扶贫开发协会" +
            "致力推动全省莲产业扶贫的重大举措。</p><p style=\"font-size:16px;border-top:0px;border-right:0px;border-bottom:0px;border-left:0px;margin:0.63em 0px 1.8em;counter-reset:list-1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7" +
            " 0 list-8 0 list-9 0;padding:0px;\">湖北水资源丰富，莲藕种植历史悠久，种植面积达150多万亩，在全国首屈一指。莲产业在我省经济作物中占有重要位置，但因为缺信息、技术、资金等原因，莲产业发展仍存在基地规模不大、产出效益不高、精深加工不" +
            "够、品牌建设滞后等问题。这与“千湖之省”水域面积辽阔、河湖港汊多的地理优势形成了一定反差。</p><p style=\"font-size:16px;border-top:0px;border-right:0px;border-bottom:0px;border-left:0px;margin:0.63em 0px 1.8em;counter-reset:list-" +
            "1 0 list-2 0 list-3 0 list-4 0 list-5 0 list-6 0 list-7 0 list-8 0 list-9 0;padding:0px;\">" +
            "今年以来，湖北省扶贫开发协会为组建莲产委（联盟）先后深入到蔡甸、洪湖等有关市县调研座谈，了解湖北莲产业发展现状与前景，也有意识地去发现龙头企业及领军人物。</p></article><p> </p>";
    private HtmlSpanner htmlSpanner;

    private TextView textView;

    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1://获取图片路径列表
                    String url = (String) msg.obj;
                    Log.e("jj", "url>>" + url);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_html);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        htmlSpanner = new HtmlSpanner(this, dm.widthPixels, handler);
        setInfo();
    }

    private void setInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Spannable spannable = htmlSpanner.fromHtml(html);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(spannable);
                        textView.setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
                    }
                });
            }
        }).start();
    }
}
