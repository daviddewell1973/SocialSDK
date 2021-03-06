require(["sbt/dom", "sbt/config", "sbt/connections/controls/astream/ActivityStreamWrapper"], function(dom, config, ActivityStreamWrapper) {
    config.Properties["loginUi"] = "popup";
    var activityStreamWrapper = new ActivityStreamWrapper({
        feedUrl: "/anonymous/rest/activitystreams/@public/@all/@all?rollup=true",
        activityStreamNode: "activityStream"
    });
    
    dom.byId("activityStreamDiv").appendChild(activityStreamWrapper.domNode);
    activityStreamWrapper.startup();
});
