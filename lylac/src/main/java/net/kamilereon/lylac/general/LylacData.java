package net.kamilereon.lylac.general;

import org.bson.Document;

/**
 * 해당 클래스가 Dcoument 클래스로 부터 로딩되거나 Document 형식으로 내보낼 수 있음을 대표함
 */
public interface LylacData {
    public void load(Document doc);

    public Document getAsDocument();
}
