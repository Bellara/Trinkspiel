package de.lauverngers.challenge;

import de.lauverngers.Constants;
import de.lauverngers.objects.Challenge;
import de.lauverngers.objects.Die;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChallengeReader {

    public static List<Challenge> readChallengesFromFile() throws IOException {
        return readChallengesFromFile(Constants.CHALLENGE_FILE_PATH);
    }

    public static List<Challenge> readChallengesFromFile(String path) throws IOException {
        final List<Challenge> challengeList = new ArrayList();
        final File xmlFile = new File(path);

        if (xmlFile.exists() && xmlFile.isFile() && xmlFile.canRead() && xmlFile.length() > 0) {
            try {
                final SAXBuilder builder = new SAXBuilder();
                final Document document = builder.build(xmlFile);
                final Element rootElement = document.getRootElement();

                final List<Element> challengeElements = rootElement.getChildren("challenge");

                for (int i = 0; i < challengeElements.size(); i++) {
                    final Element element = challengeElements.get(i);

                    final String title = element.getAttributeValue("title");
                    final String text = element.getText();

                    //when points are NULL roll a die
                    final String pointString = element.getAttributeValue("credits");
                    Integer points = null;
                    if(StringUtils.isNotEmpty(pointString)) {
                        points = Integer.valueOf(pointString);
                    }

                    final String lifeTimeString = element.getAttributeValue("round");
                    Integer lifeTime = null;
                    if(StringUtils.isNotEmpty(lifeTimeString)) {
                        lifeTime = Integer.valueOf(lifeTimeString);
                    }

                    final String action = element.getAttributeValue("action");

                    challengeList.add(new Challenge(i, title, text, points, lifeTime, action));

                }
            }
            catch (JDOMException e) {
                throw new RuntimeException(e);
            }
        }

        return challengeList;
    }

}
