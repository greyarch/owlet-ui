(ns owlet-ui.components.activity.title)

(defn activity-title [title author]
  [:div.activity-title-wrap
   [:h1 [:mark.white.box-shadow title]]
   [:h5.author "Posted by: " author]])