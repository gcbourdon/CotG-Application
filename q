[1mdiff --git a/src/main/java/game/views/VerificationView.java b/src/main/java/game/views/VerificationView.java[m
[1mindex fd4d131..b86f80c 100644[m
[1m--- a/src/main/java/game/views/VerificationView.java[m
[1m+++ b/src/main/java/game/views/VerificationView.java[m
[36m@@ -98,7 +98,7 @@[m [mpublic class VerificationView implements GameView {[m
         //verify code label[m
         emailVerificationLabel = new JLabel(String.format("Send a verification code to '%s' ?", userEmail));[m
         emailVerificationLabel.setPreferredSize(new Dimension(500, 50));[m
[31m-        emailVerificationLabel.setFont(new Font("Sans-serif", Font.PLAIN, 20));[m
[32m+[m[32m        emailVerificationLabel.setFont(new Font("Sans-serif", Font.PLAIN, 15));[m
 [m
         //send button[m
         sendButton = new JButton("send code");[m
