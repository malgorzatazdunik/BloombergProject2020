package fx;

import java.util.Comparator;

class CurrencyExchangeDataComparator implements Comparator<CurrencyExchangeData>{ 
    
    public int compare(CurrencyExchangeData first, CurrencyExchangeData second) {
        return first.getTimestamp().compareTo(second.getTimestamp()); 
    } 
} 
