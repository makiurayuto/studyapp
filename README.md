# 📚 Study Time Tracker

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6-yellow)
![Chart.js](https://img.shields.io/badge/Chart.js-Graph-blue)
![FullCalendar](https://img.shields.io/badge/FullCalendar-UI-purple)

---

## 🎯 プロジェクト概要

学習習慣の継続に課題を感じ、勉強時間を記録・可視化するWebアプリを開発しました。
カレンダーとグラフを組み合わせることで、日々の学習状況と長期的な推移を直感的に把握できる設計としています。

---

## 🧠 課題と解決

### ❌ 課題

* 勉強時間を記録しても振り返りづらい
* 継続状況が可視化されずモチベーションが低下
* 学習量を定量的に把握できない

### ✅ 解決

* カレンダーで日別の記録を直感的に可視化
* グラフで週・月単位の学習傾向を分析
* KPI表示で現在の学習量を即時確認可能に

---

## 🚀 成果

* 勉強時間を週・月単位で可視化できるようになり、学習の振り返りが容易になった
* カレンダー・グラフ・KPIの組み合わせにより、学習状況を多角的に把握可能になった
* データ構造とUI設計を分離することで、拡張しやすい設計を実現した

---

## 🛠 技術構成

### バックエンド

* Java 17
* Spring Boot 3.5.13
* Maven Wrapper（mvnw使用）
* REST API（設計・実装）

### フロントエンド

* HTML / CSS / JavaScript（フロントエンド）
* FullCalendar（カレンダーUI）
* Chart.js（データ可視化）

### その他

* fetch API（非同期通信）
* JSONデータ連携

---

## 🎥 デモ動画

[▶ アプリの操作動画を見る](https://youtu.be/nAi_jbM3V0o)
---

## 🖼 デモ / 画面


### 📅 カレンダー画面

![calendar](./images/calendar.png)

### 📊 週グラフ

![weekly](./images/weekly.png)

### 📈 月グラフ

![monthly](./images/monthly.png)

### 🎯 KPI表示

![kpi](./images/kpi.png)

---


## 💡 技術的工夫

* **状態管理のシンプル化**

* 日付ベースで学習データを統一管理し、集計処理をシンプル化
* フロントとバックエンドをREST APIで分離し、責務を明確化
* Chart.jsとFullCalendarを組み合わせ、直感的な可視化UIを実現
* 非同期通信（fetch API）により、ページ遷移なしでデータ更新可能に設計

* **UI/UX重視設計**

  * カレンダークリックのみで記録可能
  * グラフを自動更新し分析コスト削減

* **データの可視化**

  * 週・月の2軸で分析可能に設計
  * KPI（今週・今月）で即時把握

---

## 🔄 改善プロセス

初期は「記録のみ」の機能でしたが、

* 見返しづらい
* 継続につながらない

という課題が発生。

👉 以下を追加

* グラフ機能
* KPI表示
* UI改善

結果として、単なる記録ツールではなく「学習の継続と振り返りを支援するアプリ」に改善しました。

---

## 📦 セットアップ

```bash
git clone https://github.com/your-username/study-tracker.git
cd study-tracker
```

```bash
./mvnw spring-boot:run
```

👉 http://localhost:8080

---

## 🔮 今後の展望

* ログイン機能（ユーザー管理）
* スマホ対応（レスポンシブ）
* 学習カテゴリ分析
* 目標設定機能

---

## 👤 作者

Yuto Makiura

---

## 📝 補足

本アプリでは、
「課題発見 → 解決 → 改善」のプロセスを意識して開発しました。

特に、

* ユーザー視点でのUI設計
* データの可視化による課題解決

に注力しており、実務でも活かせる開発経験を得ました。
