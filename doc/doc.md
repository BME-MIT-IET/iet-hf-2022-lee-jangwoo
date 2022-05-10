# IET HF dokumentáció
## 1. feladat Build keretrendszer + CI beüzemelése
**Gradle** build keretrendszert használtunk. Ehhez a projektet át kellett alakítani. Meg kellett változtatni a mappaszerkezetet és letre kellett hozni a Gradle-hez szükséges fájlokat.
**Github Actions** a CI eszköz, ehhez a gradle.yml fájlban találhatóak a beállítások. A build lefut minden main branchre érkező push-nál és pull request-nél. 