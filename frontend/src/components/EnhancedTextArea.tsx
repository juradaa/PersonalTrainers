import {useState} from "react";

type Props = (React.DetailedHTMLProps<React.TextareaHTMLAttributes<HTMLTextAreaElement>, HTMLTextAreaElement>) & ({
    validationMessage: string
    labelMessage: string
})
export const EnhancedTextArea = (props: Props) => {
    const [isInvalid, setIsInvalid] = useState(false);
    const {validationMessage, labelMessage, className, ...rest} = props;
    return (
        <div className="relative m-2">

            <label className="block text-violet-900 text-lg" htmlFor={rest.id}>{labelMessage}</label>
            {isInvalid && <span className="peer-valid:hidden block text-red-700 text-lg absolute right-0 top-0">{validationMessage}</span>}
            <textarea className={`w-full border-black valid:border-black rounded-md peer ${isInvalid ? "border-red-700" : ""}  ${className}`}
                   onInvalid={() => setIsInvalid(true)}
                   {...rest}/>
        </div>
    );
};
