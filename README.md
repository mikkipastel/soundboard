# Emoji Soundboard
แอพพลิเคชั่น soundboard ที่ใช้ emoji ในการช่วยจำว่าช่องนี้เราใส่เสียงอะไรไว้บ้าง เป็นแอพแอนดรอยด์
(ผลงานระหหว่างร่วมงาน Stupid Hackathon Thailand 5)

## คุณเคยประสบปัญหาเหล่านี้หรือไหม?
- หา sound effect เหมาะๆเปิดไม่ได้
- พอแอพเหมือนตัว soundboard จริงที่เป็นสีๆ เราก็จำไม่ได้ว่าข่องนี้เราใส่เสียงอะไรไว้
- ประชุมใน Butter.us แล้วยังไม่มี sound effect ที่ถูกใจ

ปัญหาเหล่านี้จะหมดไป เมื่อใช้ Emoji Soundboard

## solution
ตัวแอพจะมี 8 ช่อง เพื่อให้พอดีกับขนาดนหน้าจอให้มากที่สุด
ใช้ emoji ของเสียง เพื่อให้สามารถรู้ได้ว่า ช่องนี้ใส่เสียงอะไร

<img src="/readme/01_idea.png" width="500">

หน้าจอการทำงานคร่าวๆ ออกแบบบน Figma
จะมีหน้าจอ Splash Screen เข้ามาก่อน
จากนั้นจะเป็นหน้าจอของตัว soundboard ที่มี 8 ช่อง
เมื่อยังไม่ได้ใส่เสียง สามารถกดปุ่มเพื่อใส่เสียงได้
และเมื่อใส่เสียงแล้ว สามารถกดเพื่อเล่นเสียงได้

หน้าที่เลือกเสียงเราสามารถ preview เสียงก่อนเลือกได้ด้วยนะ

![figma](/readme/02_figma_design.png)

## Screenshot

หน้าตาของแอพ Emoji Soundboard
ตัวแอพมีเสียงทั้งสิ้น 25 เสียงด้วยกันในตอนนี้

<p float="center">
  <img src="/readme/03_screen_shot_empty.png" width="200">
  <img src="/readme/04_screen_shot_choose_sound.png" width="200">
  <img src="/readme/05_screen_shot_pad.png" width="200">
</p>

## Download
รอแก้บัคก่อน เดี๋ยวเอาขึ้น Play Store นะ

### Bug list

เดี๋ยวจะมาเก็บต่อจ้า
*  [หน้าเลือกเสียง] มีบาง view ที่แสดงเบิ้ลอยู่
*  [หน้าเลือกเสียง] เมื่อทำการ reset view แล้ว app crash เนื่องจากหา view holder ไม่เจออ
*  [หน้าหลัก] ปัญหาการแสดง position หลังจากกลับเข้าแอพมาใหม่ 
