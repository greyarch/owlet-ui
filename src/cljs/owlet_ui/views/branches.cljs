(ns owlet-ui.views.branches
  (:require [re-frame.core :as re]
            [owlet-ui.components.branch :refer [branch]]))

;; TODO: (hayden, david) remove branch coloring logic from component
;; move into data model

(defn- pair-color [activity-branches]
  (let [colors ["#FF563E" "#00cbb2" "#3d8142" "#41bba2" "#e1bb00" "#254e68" "#dd0067" "#d37fe6" "#e00000"]]
    (map vector colors activity-branches)))

(defn branches-view []
  (let [activity-branches (re/subscribe [:activity-branches])]
    [:div.branches
     (if @activity-branches
       [:section
         [:h1#title [:mark "Get started by choosing a branch below"]]
         [:br]
         (let [color-pairs (pair-color (sort (:branches @activity-branches)))]
           (for [pair color-pairs]
             ^{:key (gensym "branch-")}
             [branch pair]))]
       [:h1#title [:mark "No branches available, come back soon!"]])]))