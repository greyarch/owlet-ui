(ns owlet-ui.views.branch-activities
  (:require [re-frame.core :as rf]
            [owlet-ui.components.activity-thumbnail :refer [activity-thumbnail]]))

(defn branch-activities-view []
  (let [{:keys [display-name activities]} @(rf/subscribe [:activities-by-branch-in-view])]
    [:div.outer-height-wrap
     [:div.inner-height-wrap
      [:div.breadcrumb-wrap
       [:div
        [:a {:href "#/branches"}
         [:img {:src "img/back.png"}]]]
       [:div
        [:a {:href "#/branches"}
         [:p "ALL BRANCHES"]]]]
      [:div.container-fluid.branch-activities-wrap
       (if-not display-name
         [:h2 [:mark.white.box.box-shadow [:b "This branch does not exist"]]]
         [:h2 [:mark.white.box-shadow [:b display-name]]
          [:div.flexcontainer-wrap
           (if (seq activities)
             (for [activity activities
                   :let [fields (:fields activity)
                         id (get-in fields [:preview :sys :id] (gensym "key-"))
                         entry-id (get-in activity [:sys :id])]]
               ^{:key id} [activity-thumbnail fields entry-id])
             [:p.no-activities [:mark "No activities in this branch yet. Check back soon."]])]])]]]))

