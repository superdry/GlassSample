package com.tonchidot.tab.glasssample;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;
import com.google.android.glass.widget.CardBuilder;
import com.tonchidot.tab.glasssample.model.Item;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * A {@link Service} that publishes a {@link LiveCard} in the timeline.
 */
public class LiveCardService extends Service {

    private static final String LIVE_CARD_TAG = "LiveCardService";

    private LiveCard mLiveCard;

    private ArrayList<Item> items;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        items = initItems();
        if (mLiveCard == null) {
            mLiveCard = new LiveCard(this, LIVE_CARD_TAG);

            CardBuilder builder = new CardBuilder(
                    this, CardBuilder.Layout.TEXT)
                    .setText(String.format("近くに %d 件のアイテムがあります", items.size()))
                    .addImage(items.get(0).getImageId())
                    .setFootnote(String.format("loc: %.6f, %.6f",Config.DEMO_LAT,Config.DEMO_LON))
                    .setAttributionIcon(R.drawable.statusbar_icon)
                    .setTimestamp("just now");
            RemoteViews remoteViews = builder.getRemoteViews();
            mLiveCard.setViews(remoteViews);

            // Display the options menu when the live card is tapped.
            Intent menuIntent = new Intent(this, ItemCardScrollActivity.class);
            menuIntent.putParcelableArrayListExtra("ITEM_LIST", items);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
            mLiveCard.publish(PublishMode.REVEAL);
        } else {
            mLiveCard.navigate();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mLiveCard != null && mLiveCard.isPublished()) {
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }

    private ArrayList<Item> initItems(){
        ArrayList<Item> items = new ArrayList<>();

        items = addItem(items,
                "本格イタリアンが気軽に食べられるレストラン\uD83C\uDFB5丸ビル５階",
                "ランチ利用オススメ！ コの字型のカウンターがキッチンを囲っている店内。  ▪ランチコース 1680円 前菜、メインが数種類のなかから選べて、最後に本日のデザートかエスプレッソを頼める。 前菜もメインも、アップグレードのものや種類がそれぞれ5、6種類から選べるのも嬉しいっo(^▽^)o  今日は彩りが食欲をそそる、 フルーツトマトとパンチェッタ、バジルをあわせたパスタをチョイス！  塩加減とトマトの甘みが最高でした(*ﾟ▽ﾟ*)☆  前菜のカルパッチョも本格的な味で大満足でしたっ！！  ＥＳＳＥＮＺＡ 丸ビル ５階",
                "エッセンツァ",
                R.drawable.item1,
                35.680969259999998f,
                139.76421846f,
                "東京都千代田区丸の内2-4-1 5F"
        );
        items = addItem(items,
                "夏季限定メニュー登場!!",
                "＜Photo 01> アメリカンショートケーキ（マンゴー） 販売価格：1,296円（税込価格） 販売期間：4月14日（月）～8月31日（日） ≪ＫＩＴＴＥ丸の内店限定商品≫  人気のアメリカンショートケーキに「マンゴー味」が登場。マンゴーの果肉はもちろん、ソースもたっぷりトッピングした季節を感じられるメニューです。ひんやりと冷たいココナッツミルクに浸されたスポンジケーキがあとを引くＫＩＴＴＥ丸の内店限定メニューです。    スペシャルグレープフルーツジェリー　～夏の彩りフルーツを添えて～　 販売価格：1,620円（税込価格） 販売期間：4月14日（月）～8月31日（日） ≪ＫＩＴＴＥ丸の内店限定商品≫  1年を通して人気のある「グレープフルーツジェリー」にさらにたっぷりと果肉をあしらい、より華やかに、より爽やかなデザートに仕上げました。これから夏を彩る国内外のフルーツと共にお楽しみください。",
                "日本橋千疋屋総本店フルーツパーラー",
                R.drawable.item2,
                35.679799000000003f,
                139.764545f,
                "東京都中央区日本橋室町2-1-2 2F"
        );
        items = addItem(items,
                "【味噌汁】美噌元",
                "日常のほっとするひと時を大切にして頂きたく始めた味噌汁専門店『美噌元』。味噌汁専門店ならではの具をたくさん入れた味噌汁を主役に、ヘルシーなメニューをお店でお召し上がり頂けるほか、人気のギフト「美噌汁最中」の販売も行います。  住所 東京都千代田区丸の内2-7-2 JPタワーKITTE B1 アクセス  ＪＲ 東京駅 丸の内南口 徒歩1分 地下鉄丸ノ内線 東京駅 徒歩1分 地下鉄千代田線 二重橋前駅 徒歩2分  TEL 03-6256-0831 営業時間  月～土10:00～21:00（L.O.20:30） 日・祝日　10:00～20:00（L.O.19:30） 定休日 年中無休",
                "美噌元 ＫＩＴＴＥ店",
                R.drawable.item3,
                35.679640999999997f,
                139.765086f,
                "東京都千代田区丸の内2丁目7−2 B1F"
        );
        items = addItem(items,
                "小麦粉の香りに心奪われます…「VIRON」",
                "このお店の小麦粉はフランス直輸入のレトロドールというものを使用したバケットが美味しい！ お店の前でも小麦粉の香り高さに驚かされますが、実際に食べてみると他のバケットとはケタはずれに違います！！ 一度食べたら忘れられないでしょう。 また種類豊富なカスクルート(サンドウィッチ)は絶品！！是非ご賞味を！！",
                "Brasserie VIRON 丸の内店",
                R.drawable.item4,
                35.6786447f,
                139.7651709f,
                "東京都千代田区丸の内2-7-3 1F"
        );
        items = addItem(items,
                "アイスクリーム入りチョコレートボールにショコラショーをかける新デザート！",
                "冷たいバニラアイスクリームと香ばしいクランブル（小麦粉、バター、卵などで作られたソフトなクッキーを細かく砕いたもの）をしのばせたボール状のチョコレートに、温かいショコラショーをかける魅力的なデザート「アヴァランシュ」です。",
                "ドゥバイヨル 丸の内オアゾ店",
                R.drawable.item5,
                35.681722000000001f,
                139.76675399999999f,
                "東京都千代田区丸の内1-6-4 1F"
        );
        items = addItem(items,
                "1919年パリで創業した紅茶専門店が丸の内に",
                "ベッジュマン＆バートンは、ダブリンとイギリスで紅茶を学んだアーサー・ベッジュマン氏が1919年パリで紅茶の専門店を開いたのが始まりです。ベッジュマン氏により世界に先駆けて開発されたフレーバーは他の追随を許さないオリジナリティーあふれる最高級なものばかりです。現在はその品質の高さが認められ、フランスで最も権威あるグルメ誌「LE GUIDE DES GOURMANS」で紅茶部門の「金賞」を受賞するなど名実ともに広く認められており、パリの五つ星ホテルや一流レストラン等でも多く採用されています。",
                "ベッジュマン＆バートン 丸の内店",
                R.drawable.item6,
                35.6841419f,
                139.7643974f,
                "東京都千代田区丸の内1-4-1 1F "
        );
        items = addItem(items,
                "釜たけうどん",
                "大阪讃岐うどんというジャンルを確立したお店。 甘めの出汁にモッチモチした麺とあっつあつの天ぷらの相性は抜群!! 大阪スタイルの讃岐うどんをぜひお試しあれっ♪",
                "釜たけうどん 八重洲北口店",
                R.drawable.item7,
                35.6824786f,
                139.7680664f,
                "東京都千代田区丸の内1-9-1 1F"
        );
        items = addItem(items,
                "「南インド料理ダクシン 八重洲店（東京,京橋/カレー,インド料理） : B級グルメランチ紀行",
                "南インド料理ダクシン 八重洲",
                "ダクシン八重洲店",
                R.drawable.item8,
                35.677838f,
                139.76875900000005f,
                "東京都中央区八重洲2-5-12"
        );
        items = addItem(items,
                "銀座で有機野菜と豊富なワインでおもてなしするイタリアン＆スペインレストラン",
                "HONAでは全国各地の契約農家さんから届く、安心安全！産直有機野菜や新鮮鮮魚を、スペインやイタリアのBARで提供される、バール(居酒屋)料理でご提供いたします♪ 自慢の食材は契約農家さんからの産地直送有機野菜にこだわり、ビオワインと一緒にお出迎え致します。 銀座の隠れ家的イタリアンを記念日、デート等多彩なシーンで！ その他結婚式の二次会から歓送迎会、女子会など各種パーティー・貸切も承っております。 また、ランチ営業もしておりますので、お気軽に本格イタリアンをお楽しみいただけます。 日比谷・有楽町から銀座一丁目まで広範囲で展開しております。 是非お近くの健康イタリアンへお立ち寄りください。",
                "ベジバールHONA銀座",
                R.drawable.item9,
                35.6728862f,
                139.7630814f,
                "東京都中央区銀座3-3-9 B1F"
        );
        items = addItem(items,
                "「銀座 篝」の鶏白湯SOBAと煮干醤油SOBA",
                "2014-05-27 【このチャーシューがすごい！2014】すごいチャーシューが食べられる東京のラーメン屋さんまとめ よりリンク",
                "銀座 篝",
                R.drawable.item10,
                35.6722946f,
                139.7630036f,
                "東京都中央区銀座4-4-1 1F"
        );

        return items;
    }

    private ArrayList<Item> addItem(ArrayList<Item> items, String title ,String desc, String place_name, int imageId,float lat, float lon, String address) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(desc);
        item.setPlaceName(place_name);
        Item.PlaceGeo geo = new Item.PlaceGeo();
        geo.setLat(lat);
        geo.setLon(lon);
        item.setPlaceGeo(geo);
        item.setImageId(imageId);
        item.setAddress(address);
        items.add(item);
        return items;
    }
}
