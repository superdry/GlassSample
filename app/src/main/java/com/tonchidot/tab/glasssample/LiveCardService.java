package com.tonchidot.tab.glasssample;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;
import com.google.android.glass.widget.CardBuilder;
import com.tonchidot.tab.glasssample.model.Item;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

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
                    .setText(String.format("近くに%d件のアイテムがあります", items.size()))
                    .addImage(items.get(0).getImageId())
                    .setFootnote("please click")
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
                "【味噌汁】美噌元",
                "日常のほっとするひと時を大切にして頂きたく始めた味噌汁専門店『美噌元』。味噌汁専門店ならではの具をたくさん入れた味噌汁を主役に、ヘルシーなメニューをお店でお召し上がり頂けるほか、人気のギフト「美噌汁最中」の販売も行います。  住所 東京都千代田区丸の内2-7-2 JPタワーKITTE B1 アクセス  ＪＲ 東京駅 丸の内南口 徒歩1分 地下鉄丸ノ内線 東京駅 徒歩1分 地下鉄千代田線 二重橋前駅 徒歩2分  TEL 03-6256-0831 営業時間  月～土10:00～21:00（L.O.20:30） 日・祝日　10:00～20:00（L.O.19:30） 定休日 年中無休",
                "美噌元 ＫＩＴＴＥ店",
                 R.drawable.item0,
                35.679640999999997f,
                139.765086f);
        items = addItem(items,
                "本格イタリアンが気軽に食べられるレストラン\uD83C\uDFB5丸ビル５階",
                "ランチ利用オススメ！ コの字型のカウンターがキッチンを囲っている店内。  ▪ランチコース 1680円 前菜、メインが数種類のなかから選べて、最後に本日のデザートかエスプレッソを頼める。 前菜もメインも、アップグレードのものや種類がそれぞれ5、6種類から選べるのも嬉しいっo(^▽^)o  今日は彩りが食欲をそそる、 フルーツトマトとパンチェッタ、バジルをあわせたパスタをチョイス！  塩加減とトマトの甘みが最高でした(*ﾟ▽ﾟ*)☆  前菜のカルパッチョも本格的な味で大満足でしたっ！！  ＥＳＳＥＮＺＡ 丸ビル ５階",
                "エッセンツァ",
                R.drawable.item1,
                35.680969259999998f,
                139.76421846f
                );
        items = addItem(items,
                "夏季限定メニュー登場!!",
                "＜Photo 01> アメリカンショートケーキ（マンゴー） 販売価格：1,296円（税込価格） 販売期間：4月14日（月）～8月31日（日） ≪ＫＩＴＴＥ丸の内店限定商品≫  人気のアメリカンショートケーキに「マンゴー味」が登場。マンゴーの果肉はもちろん、ソースもたっぷりトッピングした季節を感じられるメニューです。ひんやりと冷たいココナッツミルクに浸されたスポンジケーキがあとを引くＫＩＴＴＥ丸の内店限定メニューです。    スペシャルグレープフルーツジェリー　～夏の彩りフルーツを添えて～　 販売価格：1,620円（税込価格） 販売期間：4月14日（月）～8月31日（日） ≪ＫＩＴＴＥ丸の内店限定商品≫  1年を通して人気のある「グレープフルーツジェリー」にさらにたっぷりと果肉をあしらい、より華やかに、より爽やかなデザートに仕上げました。これから夏を彩る国内外のフルーツと共にお楽しみください。",
                "日本橋千疋屋総本店フルーツパーラー",
                R.drawable.item2,
                35.679799000000003f,
                139.764545f
        );
        items = addItem(items,
                "東京ドッグ",
                "アメリカ発、東京で進化を続けるホットドッグ",
                "東京DOG",
                R.drawable.item3,
                35.681722000000001f,
                139.76675399999999f);
        items = addItem(items,
                "アイスクリーム入りチョコレートボールにショコラショーをかける新デザート！",
                "冷たいバニラアイスクリームと香ばしいクランブル（小麦粉、バター、卵などで作られたソフトなクッキーを細かく砕いたもの）をしのばせたボール状のチョコレートに、温かいショコラショーをかける魅力的なデザート「アヴァランシュ」です。",
                "ドゥバイヨル 丸の内オアゾ店",
                R.drawable.item4,
                35.681722000000001f,
                139.76675399999999f);
        items = addItem(items,
                "オフィス街の本格ハンバーガー屋さん♫",
                "久屋〜丸の内エリアを歩いて発見、本格的な手作りハンバーガー屋さん。アメリカンな店員さんがかわいい。味は濃いめ、しっかり塩コショウが効いてます！席数18で、宅配もOKなので忙しいオフィスランチに最適、12:10には満席の人気店。男性人気NO.1はレイヤーズ、女性人気NO.1はアボカドチーズ。ランチ980円。",
                "LAYER'S HAMBURGER",
                R.drawable.item10,
                35.679799000000003f,
                139.764545f);
        return items;
    }

    private ArrayList<Item> addItem(ArrayList<Item> items, String title ,String desc, String place_name, int imageId,float lat, float lon) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(desc);
        item.setPlaceName(place_name);
        Item.PlaceGeo geo = new Item.PlaceGeo();
        geo.setLat(lat);
        geo.setLat(lon);
        item.setPlaceGeo(geo);
        item.setImageId(imageId);
        items.add(item);
        return items;
    }
}
