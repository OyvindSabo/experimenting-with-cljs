(ns app.core
  (:require
   [reagent.core :as r]))

(defn section-container
  [children]
  [:div
   {:style
    {:border "2px dashed #dcdde1"
     :display "flex"
     :margin "20px auto"
     :padding "20px"
     :width "1024px"}}
   children])

(defn title [children] [:h1 {:style {:margin "auto"}} children])

(def selected-menu (r/atom 0))

(defn menu-item [props & children]
  [:div {:style {:flex-grow "1" :text-align "center"}}
   [:span {:on-click (:on-click props) :style {:color "#487eb0" :cursor "pointer"}}
    children]])

(defn app []
  [:div
   [section-container [title "Øyvind Sæbø"]]
   [section-container
    [:<>
     [menu-item {:on-click #(swap! selected-menu (fn [] 0))} "menu item 1"]
     [menu-item {:on-click #(swap! selected-menu (fn [] 1))} "menu item 2"]]]
   [section-container (case @selected-menu 0 "Menu item 1 selected" 1 "Menu item 2 seleceted")]])

(defn ^:dev/after-load start
  []
  (r/render [app] (.getElementById js/document "app")))

(defn ^:export main [] (start))
